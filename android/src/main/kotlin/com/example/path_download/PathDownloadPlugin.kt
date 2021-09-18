package com.example.path_download

import android.content.Context
import android.os.Environment.*
import android.util.Log
import android.widget.Switch
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.io.File

/** PathDownloadPlugin */
class PathDownloadPlugin: FlutterPlugin, MethodCallHandler {

  private lateinit var methodChannel : MethodChannel
  private lateinit var applicationContext: Context

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
      methodChannel = MethodChannel(flutterPluginBinding.binaryMessenger, "path_download")
      methodChannel.setMethodCallHandler(this)
      applicationContext = flutterPluginBinding.applicationContext
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {

    if (call.method == "getDownloadsDirectory"){
      val typeFile : String? = call.argument("typeFile")
      result.success(getDownloadsDirectory(typeFile))
    }
    else result.notImplemented()

  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    methodChannel.setMethodCallHandler(null)
  }

  private fun getDownloadsDirectory(typeFile : String?): String? {

    val downloads: File? = applicationContext.getExternalFilesDir(DIRECTORY_DOWNLOADS)
    downloads?.let { Log.i("DIRECTORY_DOWNLOADS", it.absolutePath) }

    val pictures: File? = applicationContext.getExternalFilesDir(DIRECTORY_PICTURES)
    pictures?.let { Log.i("DIRECTORY_PICTURES", it.absolutePath) }

    val music: File? = applicationContext.getExternalFilesDir(DIRECTORY_MUSIC)
    music?.let { Log.i("DIRECTORY_MUSIC", it.absolutePath) }

    val dcim: File? = applicationContext.getExternalFilesDir(DIRECTORY_DCIM)
    dcim?.let { Log.i("DIRECTORY_DCIM", it.absolutePath) }

    val movies: File? = applicationContext.getExternalFilesDir(DIRECTORY_MOVIES)
    movies?.let { Log.i("DIRECTORY_MOVIES", it.absolutePath) }

    return downloads?.absolutePath
  }
}
