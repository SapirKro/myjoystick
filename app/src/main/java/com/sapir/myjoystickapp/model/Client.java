package com.sapir.myjoystickapp.model;

import androidx.annotation.NonNull;

public class Client {
    @NonNull
    private String mIP;
    @NonNull
    private String mPort;

    public Client(@NonNull final String ip, @NonNull final String port) {
        mIP = ip;
        mPort = port;
    }

    @NonNull
    public String getIP() {
        return mIP;
    }

    public void setIP(@NonNull final String ip) {
        mIP = ip;
    }

    @NonNull
    public String getPort() {
        return mPort;
    }

    public void setPort(@NonNull final String Port) {
        mPort = Port;
    }


}