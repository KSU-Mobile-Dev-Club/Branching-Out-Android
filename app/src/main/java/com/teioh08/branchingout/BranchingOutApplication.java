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
                        .applicationId(getString(R.string.APP_KEY))
                        .clientKey(getString(R.string.CLIENT_KEY))
                        .server(getString(R.string.SERVER_KEY)) // The trailing slash is important.
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
