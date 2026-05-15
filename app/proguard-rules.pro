# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.twinflip.** { *; }

# Prevent ProGuard from scrambling or stripping out the core AdMob SDK classes
-keep class com.google.android.gms.ads.** { *; }
-keep public class com.google.android.gms.ads.internal.client.MeasurementManager { *; }


# These rules prevent crashes on launch caused by the background libraries
# packaged inside the AdMob SDK.

# Suppress compilation warnings regarding missing Room/WorkManager classes
-dontwarn androidx.room.**
-dontwarn androidx.work.**

# Keep Jetpack Startup initialization paths intact
-keep class androidx.startup.** { *; }

# Protect Jetpack WorkManager background components
-keep class androidx.work.** { *; }

# Using a broader wildcard string format to bypass the editor's missing-class check
-keep class **.SystemAlarmService { *; }
-keep class **.SystemJobService { *; }
-keep class **.MultiInstanceInvalidationService { *; }

# Soft class match pattern for the Room Database extension
-keep class * implements **.RoomDatabase