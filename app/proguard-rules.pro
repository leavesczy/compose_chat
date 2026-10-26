-repackageclasses
-allowaccessmodification
-dontusemixedcaseclassnames
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

-assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** d(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
    public static *** wtf(...);
}

-keep class com.tencent.imsdk.** { *; }