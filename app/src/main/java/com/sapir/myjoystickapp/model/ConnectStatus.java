package com.sapir.myjoystickapp.model;

import com.sapir.myjoystickapp.viewmodels.LoginViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class ConnectStatus extends Observable {
    private final List<LoginViewModel> observers = new ArrayList<>();


    public int mystate;
    public ConnectStatus(LoginViewModel  o){
        mystate=-1;
        this.observers.add(o);
    }
    public void setConnectStatus(int num){

        mystate=num;
        notifyAllObservers();
    }


    private void notifyAllObservers() {
        for (LoginViewModel  observer : observers) {
            observer.update();
        }
    }
}
