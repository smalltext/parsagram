package com.smalltext.parsagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("pWhzciunMmg83bGDwhD3Ja20Fd3aei3tOdbwBg6q")
                .clientKey("OdB9XhOYU2OB2tVEAI7YPFMzVPkCFQ7NtobqY0IH")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
