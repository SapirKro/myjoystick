package com.journaldev.androidmvvmbasics.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.journaldev.androidmvvmbasics.BR;
import com.journaldev.androidmvvmbasics.model.MyThreadPool;
import com.journaldev.androidmvvmbasics.model.User;
import com.journaldev.androidmvvmbasics.model.clientToServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginViewModel extends BaseObservable {
    private User user;
    private clientToServer c;
    ExecutorService pool;

    private String successMessage = "Login was successful";
    private String errorMessage = "IP or Port not valid";

    @Bindable
    public String toastMessage = null;


    public String getToastMessage() {
        return toastMessage;
    }


    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public LoginViewModel() {

        user = new User("", "");
       this.c= new clientToServer();
       this.pool= Executors.newFixedThreadPool(1);
    }

    public void afterEmailTextChanged(CharSequence s) {

        user.setEmail(s.toString());
        c.setIP(s.toString());

    }

    public void afterPortTextChanged(CharSequence s) {
        user.setPort(s.toString());
        String Port=s.toString();
        int myport= Integer.parseInt(Port);
        c.setPort(myport);
    }

    public void onLoginClicked() {

        if (c.isClientConnet(this.pool)){      ///  if (user.isInputDataValid())
            setToastMessage(successMessage);
        /*    ExecutorService pool = Executors.newFixedThreadPool(1);
            Runnable r1 = new MyThreadPool.sendData(c,1);
            pool.execute(r1);*/
        }
        else{            setToastMessage(errorMessage);}
    }
}