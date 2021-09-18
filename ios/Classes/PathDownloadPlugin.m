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
