Ultimate Android App Template 2
==========================

### A simple app template. Nothing fancy. Material Design in red accents.


![alt text](https://github.com/AndreiD/UltimateAndroidAppTemplate/blob/master/app/device-2015-10-22-191003.png "How the app looks 1")

![alt text](https://github.com/AndreiD/UltimateAndroidAppTemplate/blob/master/app/device-2015-10-22-191017.png "How the app looks 2")

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






