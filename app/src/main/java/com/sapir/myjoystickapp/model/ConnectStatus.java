package com.sapir.myjoystickapp.model;

import com.sapir.myjoystickapp.viewmodels.LoginViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ConnectStatus extends Observable {
    private List<LoginViewModel> observers = new ArrayList<LoginViewModel >();


    public int mystate;
    public ConnectStatus(LoginViewModel  o){
        mystate=-1;
        this.observers.add(o);
    }
    public void setConnectStatus(int num){

        mystate=num;
        notifyAllObservers();
    }
    public int getState() {
        return mystate;
    }
    public void attach(LoginViewModel observer){
        observers.add(observer);
    }
    private void notifyAllObservers() {
        for (LoginViewModel  observer : observers) {
            observer.update();
        }
    }
}
