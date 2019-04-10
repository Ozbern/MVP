package com.example.mvp;

import android.app.Application;

public class App extends Application {
    public static final String TAG = "log_tag";
    public static final String BASE_URL = "https://raw.githubusercontent.com";

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
