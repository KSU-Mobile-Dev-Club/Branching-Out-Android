package com.teioh08.branchingout;


import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class BranchingOutApplication extends Application {


    private static BranchingOutApplication aInstance;

    public BranchingOutApplication() {
        aInstance = this;

    }


    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                        .applicationId(APP_KEY)
                        .clientKey(CLIENT_KEY)
                        .server("localhost:1337/parse/") // The trailing slash is important.
                        .build()
        );

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        defaultACL.setPublicReadAccess(true);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseACL.setDefaultACL(defaultACL, true);
    }

    public static synchronized BranchingOutApplication getInstance() {
        return aInstance;
    }

}
