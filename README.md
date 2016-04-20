Ultimate Android App Template [level: Beginner]
==========================

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-UltimateAndroidAppTemplate-brightgreen.svg?style=flat)](http://android-arsenal.com/details/3/2781)

This is a simple start-template to save you a little time.

#### How to use it:

* Create a new blank android project or clone this repo
* Download the zip file for this project
* Copy paste the app folder
* Copy paste the build.gradle and modify applicationId "com.andrei.template" to your package name
* Check the compileSdkVersion, and buildToolsVersion to be the latest
* Run it and see that it's working on your emulator.
* IMPORTANT: Remove the libs that you don't need. Add those that you do. Profit!
* Star this repository :)


#### What it contains:

~~~~
apply plugin: 'com.android.application'
apply plugin: 'com.neenbedankt.android-apt'
apply plugin: 'me.tatarka.retrolambda'


android {
  compileSdkVersion 23
  buildToolsVersion '23.0.3'

  dexOptions {
    incremental true
  }

  productFlavors {
    dev {
      minSdkVersion 22
    }
    prod {
      minSdkVersion 15
    }
  }

  signingConfigs {
    config {
      keyAlias 'appcert.key'
      keyPassword 'password'
      storeFile file('location...')
      storePassword 'password'
    }
  }

  defaultConfig {
    applicationId "com.andrei.template"
    minSdkVersion 15
    targetSdkVersion 23
    versionCode 1
    versionName "1.0"
  }
  buildTypes {
    release {
      signingConfig signingConfigs.config
      minifyEnabled true
      shrinkResources true
      proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }
    debug {
      minifyEnabled false
      versionNameSuffix " Debug"
    }
  }

  compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
  }

  packagingOptions {
    exclude 'META-INF/DEPENDENCIES'
    exclude 'LICENSE.txt'
    exclude 'META-INF/LICENSE'
    exclude 'META-INF/LICENSE.txt'
    exclude 'META-INF/NOTICE'
    exclude 'LICENSE.txt'
  }

  lintOptions {
    warning 'InvalidPackage'
    abortOnError false
  }

  //needed if for espresso
  configurations.all {
    resolutionStrategy {
      force 'com.android.support:support-annotations:23.0.1'
    }
  }
}

dependencies {
  compile fileTree(include: ['*.jar'], dir: 'libs')

  //----- Support Libs
  compile 'com.android.support:appcompat-v7:23.3.0'
  compile "com.android.support:design:23.3.0"
  compile "com.android.support:recyclerview-v7:23.3.0"
  compile "com.android.support:cardview-v7:23.3.0"

  //----- EventBus
  compile 'org.greenrobot:eventbus:3.0.0'

  //----- Retrofit
  compile 'com.squareup.retrofit2:retrofit:2.0.0'
  compile "com.squareup.retrofit2:converter-gson:2.0.0"
  compile "com.squareup.retrofit2:adapter-rxjava:2.0.0"
  compile 'com.squareup.okhttp3:logging-interceptor:3.0.1'

  //----- Butterknife
  compile "com.jakewharton:butterknife:7.0.1"

  //----- Loggin
  compile 'com.github.zhaokaiqiang.klog:library:1.3.0'

  //----- Picasso
  compile 'com.squareup.picasso:picasso:2.5.2'

  //----- Annotations
  compile 'org.glassfish:javax.annotation:10.0-b28'

  //----- Rating
  compile 'com.github.hotchemi:android-rate:0.5.6'

  //----- Testing
  androidTestCompile 'com.android.support:support-annotations:23.3.0'
  androidTestCompile 'com.android.support.test:runner:0.4.1'
  androidTestCompile 'com.android.support.test.uiautomator:uiautomator-v18:2.1.1'
  androidTestCompile 'org.hamcrest:hamcrest-integration:1.3'
}

task clean(type: Exec) {
  ext.lockhunter = '\"C:\\Program Files\\LockHunter\\LockHunter.exe"'
  def buildDir = file(new File("build"))
  commandLine 'cmd', "$lockhunter", '/delete', '/silent', buildDir
}

~~~~

#### Too simple? MVP Arhitecture/RxJava/Database Template ?

[Check UltimateAndroidTemplateRx](https://github.com/AndreiD/UltimateAndroidTemplateRx)


#### Need more nice stuff ?

- Google, Facebok, Twitter logins -> https://github.com/AndreiD/FacebookTwitterGoogleLogins
- A survey lib for your app -> https://github.com/AndreiD/surveylib

#### Updates, Questions, and Requests

Ping me here :)


#### You like this project ? Check
- https://github.com/AndreiD/surveylib - A very good looking survey library
- https://github.com/AndreiD/TSnackBar Snackbar from the top


#### License

~~~~
Copyright 2015 AndroidAdvance.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
~~~~
