package com.sapir.myjoystickapp.model;

import androidx.annotation.NonNull;
///TODO CHANGE TO IP AND PORT AND EXPORT FROM XML
public class User {
    @NonNull
    private String mIP;
    @NonNull
    private String mPort;

    public User(@NonNull final String ip, @NonNull final String port) {
        mIP = ip;
        mPort = port;
    }

    @NonNull
    public String getEmail() {
        return mIP;
    }

    public void setEmail(@NonNull final String email) {
        mIP = email;
    }

    @NonNull
    public String getPort() {
        return mPort;
    }

    public void setPort(@NonNull final String Port) {
        mPort = Port;
    }


}