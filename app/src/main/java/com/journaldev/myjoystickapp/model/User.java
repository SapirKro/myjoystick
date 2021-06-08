package com.journaldev.myjoystickapp.model;

import androidx.annotation.NonNull;
///TODO CHANGE TO IP AND PORT AND EXPORT FROM XML
public class User {
    @NonNull
    private String mEmail;
    @NonNull
    private String mPort;

    public User(@NonNull final String email, @NonNull final String Port) {
        mEmail = email;
        mPort = Port;
    }

    @NonNull
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(@NonNull final String email) {
        mEmail = email;
    }

    @NonNull
    public String getPort() {
        return mPort;
    }

    public void setPort(@NonNull final String Port) {
        mPort = Port;
    }


}