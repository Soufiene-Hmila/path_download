package com.example.path_download

import android.content.Context
import android.os.Environment.*
import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.io.File
import android.provider.MediaStore
import android.R.attr.mimeType
import android.content.ContentValues
import android.content.ContentResolver
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi


/** PathDownloadPlugin */
class PathDownloadPlugin: FlutterPlugin, MethodCallHandler {

  private lateinit var methodChannel : MethodChannel
  private lateinit var applicationContext: Context

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
      methodChannel = MethodChannel(flutterPluginBinding.binaryMessenger, "path_download")
      methodChannel.setMethodCallHandler(this)
      applicationContext = flutterPluginBinding.applicationContext
  }

  @RequiresApi(Build.VERSION_CODES.Q)
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

  @RequiresApi(Build.VERSION_CODES.Q)
  private fun getDownloadsDirectory(typeFile : String?): String? {

    val fileDirectory: File? = applicationContext.getExternalFilesDir(typeFile)
    fileDirectory?.let { Log.i("$typeFile", it.absolutePath) }
    // return fileDirectory?.absolutePath

    val resolver: ContentResolver = applicationContext.contentResolver
    val contentValues = ContentValues()
    contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "Docum")
    contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, DIRECTORY_DOWNLOADS)
    val uri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
   return resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)?.path
  }
}
