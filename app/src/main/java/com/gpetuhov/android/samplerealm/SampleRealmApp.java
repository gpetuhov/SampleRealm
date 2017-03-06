package com.gpetuhov.android.samplerealm;

import android.app.Application;

import io.realm.Realm;

public class SampleRealmApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Realm initialization must be done once.
        // This is the recommended place for it.
        Realm.init(this);
    }
}
