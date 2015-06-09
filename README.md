Ultimate Android App Template
==========================

A simple app template. Nothing fancy. Material Design in red accents.

![alt text](https://github.com/AndreiD/UltimateAndroidAppTemplate/blob/master/app/device-2015-06-09-165206.png "How the app looks 1")

#### What it contains:

~~~~

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:22.2.0'
    compile 'com.android.support:support-v4:22.+'

    compile 'org.roboguice:roboguice:3.+'
    provided 'org.roboguice:roboblender:3.+'

    compile 'com.squareup.retrofit:retrofit:1.9.0'
    compile 'com.squareup.okhttp:okhttp:2.3.0'

    compile('de.keyboardsurfer.android.widget:crouton:1.8.5@aar') {
        exclude group: 'com.google.android', module: 'support-v4'
    }
    compile 'com.squareup.picasso:picasso:2.5.2'
}

~~~~

- Robo Guice powered
- Retrofit API ready to be used
- Picasso for image loading
- Crouton for a toast replacement
- Feedback contact by email for feedback / Settings Page with some dummy settings etc.






