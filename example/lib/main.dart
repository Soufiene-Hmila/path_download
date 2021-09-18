import 'dart:io';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:path_download/path_download.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {


  Directory? _downloadsDirectory;

  @override
  void initState() {
    super.initState();
    initDownloadsDirectoryState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initDownloadsDirectoryState() async {
    Directory? downloadsDirectory;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      downloadsDirectory = (await PathDownload.pathDownload);
    } on PlatformException {
      print('Could not get the downloads directory');
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _downloadsDirectory = downloadsDirectory;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Downloads Directiry example app'),
        ),
        body: Center(
          child: Text(
            _downloadsDirectory != null
                ? 'Downloads directory: ${_downloadsDirectory!.path}\n'
                : 'Could not get the downloads directory',
          ),
        ),
      ),
    );
  }
}
