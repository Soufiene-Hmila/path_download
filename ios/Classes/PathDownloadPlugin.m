#import "PathDownloadPlugin.h"
#if __has_include(<path_download/path_download-Swift.h>)
#import <path_download/path_download-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "path_download-Swift.h"
#endif

@implementation PathDownloadPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftPathDownloadPlugin registerWithRegistrar:registrar];
}
@end

@implementation PathDownloadPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  FlutterMethodChannel* channel = [FlutterMethodChannel
      methodChannelWithName:@"path_download"
            binaryMessenger:[registrar messenger]];
  PathDownloadPlugin* instance = [[PathDownloadPlugin alloc] init];
  [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  if ([call.method isEqualToString:@"getDownloadsDirectory"]) {
    result([self getDownloadsDirectory]);
  } else {
    result(FlutterMethodNotImplemented);
  }
}

- (NSString*)getDownloadsDirectory {
  NSArray* paths = NSSearchPathForDirectoriesInDomains(NSDownloadsDirectory, NSUserDomainMask, YES);
  return paths.firstObject;
}

@end