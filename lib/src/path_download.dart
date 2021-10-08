
import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class PathDownload {

  static const MethodChannel _channel = MethodChannel('path_download');

  Future<Directory?> pathDownload(String typeFile) async {
    final String? pathDownload = await _channel.invokeMethod('getDownloadsDirectory', { 'typeFile' : typeFile});
    return pathDownload!= null? Directory(pathDownload):null;
  }
}
