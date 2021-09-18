
import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class PathDownload {

  static List<String> getTypeFileDirectory() {
    return <String>[
      'A livrer',
      'Bloquer',
      'Selon En Cours',
    ];
  }

  static const MethodChannel _channel = MethodChannel('path_download');

  static Future<Directory?> get pathDownload async {
    var typeFile = "";
    final String? pathDownload = await _channel.invokeMethod('getDownloadsDirectory',{ 'typeFile' : typeFile});
    return pathDownload!= null?
    Directory(pathDownload):null;
  }
}
