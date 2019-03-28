Ultimate Android App Template [level: Beginner]
==========================

### This app template has moved to Kotlin. I strongly suggest you use Kotlin. Kotlin is a fun, powerful language and you should use it. 

## The repo is this one -> https://github.com/AndreiD/UltimateAndroidAppTemplateKotlin


## old stuff, don't use it please.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-UltimateAndroidAppTemplate-brightgreen.svg?style=flat)](http://android-arsenal.com/details/3/2781)

## This is a simple start-template to save you a little time. It doesn't use dagger // mvp // mvvp or any other stuff that requires an more in depth knowledge of android.

<img width="300px" alt="how it looks" src="https://raw.githubusercontent.com/AndreiD/UltimateAndroidAppTemplate/master/deleteme.gif"/>

#### How to use it:

Step 1:

* Fork/Clone this repo. Add a local.properties with your sdk location (it should be automatically done by android studio)
* or Manual mode: Download the zip file for this project.
* Copy paste the app folder
* Copy paste the build.gradle and modify applicationId "com.andrei.template" to your package name
* Check the compileSdkVersion, and buildToolsVersion to be the latest
* Run it and see that it's working on your emulator.
* IMPORTANT: Remove the libs that you don't need. Add those that you do.
* Star this repository

Step 2: ???
Step 3: Profit

#### What it contains:

~~~~
apply plugin: 'com.android.application'
apply plugin: 'io.objectbox'

android {
  compileSdkVersion 26
  buildToolsVersion "26.0.1"

  defaultConfig {
    applicationId "com.andrei.template"
    minSdkVersion 23
    targetSdkVersion 26
    versionCode 1
    versionName "1.0"
    vectorDrawables.useSupportLibrary = true
    testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      minifyEnabled false
      shrinkResources false
      proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }
    debug {
      minifyEnabled false
      versionNameSuffix ".debug"
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
  implementation 'com.android.support:appcompat-v7:26.1.0'
  implementation "com.android.support:design:26.1.0"
  implementation "com.android.support:recyclerview-v7:26.1.0"
  implementation "com.android.support:cardview-v7:26.1.0"


  //----- Retrofit
  compile 'com.squareup.retrofit2:retrofit:2.1.0'
  compile "com.squareup.retrofit2:converter-gson:2.1.0"
  compile "com.squareup.retrofit2:adapter-rxjava:2.1.0"
  compile 'com.squareup.okhttp3:logging-interceptor:3.7.0'

  //----- Logging
  compile 'com.github.zhaokaiqiang.klog:library:1.6.0'

  //----- Butterknife
  compile "com.jakewharton:butterknife:8.6.0"
  annotationProcessor "com.jakewharton:butterknife-compiler:8.6.0"

  //----- Picasso
  compile 'com.squareup.picasso:picasso:2.5.2'

  //----- Dialogs
  compile 'com.muddzdev:styleabletoast:1.0.6'

  //----- Database
  compile 'io.objectbox:objectbox-android:0.9.12'

  //-------- HERE ARE SOME RECOMMENDED LIBRARIES TO HELP YOU -------

//  //----- Onboarding
//  compile 'com.codemybrainsout.onboarding:onboarder:1.0.3'
//
//  //----- QRCode Scanner
//  compile 'me.dm7.barcodescanner:zxing:1.9.8'
//
//  //----- Navigation Drawer
//  compile('com.mikepenz:materialdrawer:5.9.5@aar') {
//    transitive = true
//  }
//  //----- EventBus
//  compile 'org.greenrobot:eventbus:3.0.0'
//
//  //----- Butterknife
//  compile "com.jakewharton:butterknife:7.0.1"
//
//  //----- Annotations
//  compile 'org.glassfish:javax.annotation:10.0-b28'
//
//  //----- Rating
//  compile 'com.github.hotchemi:android-rate:0.5.6'
//
//  //------  Socket library
//  compile 'com.neovisionaries:nv-websocket-client:1.25'
//
//  //badges on icons
//  compile ('com.mikepenz:actionitembadge:3.2.6@aar') {
//    transitive = true
//  }
//  //------ Custom Font
//  compile 'uk.co.chrisjenx:calligraphy:2.3.0'
//
//  //------ Charts
//  compile 'com.github.PhilJay:MPAndroidChart:v2.2.4'
//
//  //------ Calendar
//  compile 'com.squareup:android-times-square:1.6.5@aar'
//

//
//  //----- Testing
//  androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
//    exclude group: 'com.android.support', module: 'support-annotations'
//  })
//  testCompile 'junit:junit:4.12'
//  androidTestCompile 'com.android.support:support-annotations:25.1.0'
//  androidTestCompile 'com.android.support.test:runner:0.5'
//  androidTestCompile 'com.android.support.test.uiautomator:uiautomator-v18:2.1.2'
//  androidTestCompile 'org.hamcrest:hamcrest-integration:1.3'
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
- https://github.com/AndreiD/SimpleChat - Simple Realtime Room Chat in Android.
- https://github.com/AndreiD/surveylib - A very good looking survey library
- https://github.com/AndreiD/TSnackBar Snackbar from the top


#### License

~~~~
Copyright 2017 AndroidAdvance.com

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
