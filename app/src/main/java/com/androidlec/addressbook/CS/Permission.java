package com.androidlec.addressbook.CS;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permission {

    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int STORAGE_REQUEST_CODE = 200;

    Context context;

    public Permission(Context context) {
        this.context = context;
    }

    public boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
    }

    public void requestStoragePermission() {
        ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE);
    }

    public boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
    }

    public void requestCameraPermission() {
        ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
    }
}
