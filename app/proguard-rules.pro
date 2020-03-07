# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\kaifa\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#指定代码的压缩级别
-optimizationpasses 1
#预校验
-dontpreverify
#包明不混合大小写
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
 #优化  不优化输入的类文件
-dontoptimize
#混淆时是否记录日志
-verbose
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#忽略全部警告
#-ignorewarnings

##=========去除日志输入 start===============
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}

##=========去除日志输入 end===============

##记录生成的日志数据,gradle build时在本项目根目录输出##
#apk 包内所有 class 的内部结构
-dump proguard/class_files.txt
#未混淆的类和成员
-printseeds proguard/seeds.txt
#列出从 apk 中删除的代码
-printusage proguard/unused.txt
#混淆前后的映射
-printmapping proguard/mapping.txt
########记录生成的日志数据，gradle build时 在本项目根目录输出-end######

# 保持哪些类不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.support.v4.app.Fragment

##=============android.support.*  start
-keep  class android.support.v4.app.** {*;}
-keep  interface android.support.v4.app.** {*;}
-keep  class android.support.v7.** {*;}
-keep  interface android.support.v7.** {*;}

#SmoothViewPager Reflection and introspection
-keep  public class android.support.v4.view.ViewPager* {
    private <fields>;
    private static final Interpolator sInterpolator;
}
-dontwarn android.support.**
##=============android.support.*  end

#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

#保持 Parcelable 不被混淆
-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#保持枚举 enum 类不被混淆
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}

#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}

#避免混淆内部类
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

#lamada
-dontwarn java.lang.invoke.*

#webkit,javax
-keep public class javax.**
-keep public class android.webkit.**
-keep class android.webkit.JavascriptInterface {*;}

#############################################################################################
########################                 以上通用           ##################################
#############################################################################################


#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#jpush
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

#rx
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}

#for EventBus
-keepclassmembers class ** {
    public void onEvent*(**);
}
-dontwarn de.greenrobot.event.**



#http
-dontwarn org.apache.http.**
-dontwarn com.android.volley.toolbox.**

#JS call
-keep class com.luluyou.lib.hybrid.jsinterface.LLYJavascriptInterface{ public <methods>;}
-keep class com.luluyou.licai.webplugin.JavaScriptApp**{*;}

#ribot
-keep class uk.co.ribot.** {*;}
-keep interface uk.co.ribot.** {*;}

#gson
-keep class com.luluyou.lib.hybrid.jsinterface.model.** {*;}
-keep class com.luluyou.loginlib.model.** { *;}
-keep class com.ailianlian.liandou.bean.**  {*;}
-keep class com.ailianlian.liandou.api.response.** {*;}
-keep class * implements com.luluyou.loginlib.model.request.RequestModel {*;}


#for guava
-dontwarn com.google.**
-keep class com.google.** {*;}

# LeakCanary
-keep class org.eclipse.mat.** { *; }
-keep class com.squareup.leakcanary.** { *; }


-keep class org.joda.time.** {*;}
-dontwarn org.joda.**

-keep class u.aly.** {*;}
-keep class u.aly.*$* {*;}
-dontwarn u.aly.*$*
-dontwarn org.apache.http.conn.ssl.SSLSocketFactory
-dontwarn org.apache.http.auth.AuthSchemeRegistry
-dontwarn org.apache.http.cookie.CookieSpecRegistry
-dontwarn org.apache.http.client.CredentialsProvider
-dontwarn java.lang.reflect.Method
-dontwarn com.alipay.android.phone.mrpc.core**
-dontwarn java.lang.invoke.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

#-keep class rx.** {*;}
-dontwarn rx.internal.util.**



