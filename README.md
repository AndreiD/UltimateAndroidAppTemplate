Ultimate Android App Template 2
==========================

an extremely simple android template having everything you might need to get a boost start into your app.



![alt text](https://github.com/AndreiD/UltimateAndroidAppTemplate/blob/master/app/the_gif_1.gif "How the app looks 1")


#### How to use it:

* Create a new blank android project
* Download the zip file for this project
* Copy paste the app folder
* Copy paste the build.gradle and modify applicationId "com.andrei.template" to your package name
* Check the compileSdkVersion, and buildToolsVersion to be the latest
* Run it. Remove the libs that you don't need. Add those that you do. 
* Star this repository :)


#### What it contains:

~~~~

dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    apt "org.androidannotations:androidannotations:$AAVersion"
    compile "org.androidannotations:androidannotations-api:$AAVersion"
    compile 'com.android.support:appcompat-v7:23.0.1'
    compile 'com.android.support:support-v4:23+'
    compile 'com.android.support:support-annotations:23+'
    compile 'com.android.support:recyclerview-v7:23+'
    compile 'com.android.support:design:23+'
    compile 'de.greenrobot:eventbus:2.4.0'
    compile 'com.squareup.retrofit:retrofit:1.9.0'
    compile 'com.squareup.okhttp:okhttp:2.3.0'
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'com.android.support:recyclerview-v7:23+'
    compile 'com.android.support:design:23+'

    //-------- app fonts -----------
    compile 'uk.co.chrisjenx:calligraphy:2.1.0'

    //----- nice progress app ----
    compile 'com.akexorcist:RoundCornerProgressBar:2.0.3'

}

~~~~

- Powered by Android Annotations
- Retrofit API ready to be used
- Picasso for image loading
- Snackbar, RecyclerView, Pull to Refresh etc.
- Feedback contact by email for feedback / Settings Page with some dummy settings etc.


####Updates, Questions, and Requests

Ping me here :)


#### TODO://

* proguard config
* login forms
* test cases
* database examples
* event bus
