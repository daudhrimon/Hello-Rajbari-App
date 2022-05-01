package com.friendsit.hellorajbari;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class DiskPersistence extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
