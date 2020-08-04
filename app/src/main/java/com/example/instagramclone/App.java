package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("xm0vEwSrO0BDHHtLFhn53iReiBQJhA5sNZKy0TpN")
                // if defined
                .clientKey("bp1xkLl2jiRzLdBNQ0i9wc0OP4Iby82LAToaM6vq")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
