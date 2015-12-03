package com.andrei.template.permissions;

import com.andrei.template.activities.MainActivity;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class SamplePermissionListener implements PermissionListener {

    private final MainActivity activity;

    public SamplePermissionListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
        activity.showPermissionGranted(response.getPermissionName());
    }

    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
        activity.showPermissionDenied(response.getPermissionName(), response.isPermanentlyDenied());
    }

    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
                                                             PermissionToken token) {
        activity.showPermissionRationale(token);
    }
}