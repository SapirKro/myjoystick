package com.journaldev.androidmvvmbasics.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.journaldev.androidmvvmbasics.BR;
import com.journaldev.androidmvvmbasics.model.ConnectStatus;
import com.journaldev.androidmvvmbasics.model.MyThreadPool;
import com.journaldev.androidmvvmbasics.model.User;
import com.journaldev.androidmvvmbasics.model.clientToServer;
import com.journaldev.androidmvvmbasics.views.TheJoystick;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class LoginViewModel extends BaseObservable {
    private User user;
    private clientToServer c;
    ExecutorService pool;
TheJoystick joy;
    ConnectStatus s1;
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

    public LoginViewModel(JoystickView joystick) {

        user = new User("", "");
       this.c= new clientToServer();
       this.pool= Executors.newFixedThreadPool(1);
      this.s1=new ConnectStatus();
       joy=new TheJoystick(joystick,this.c);
        joy.doInChange(this.pool,this.s1);
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

    public int isClientConnet( ) {
        final CountDownLatch latch = new CountDownLatch(1);

        s1.mystate=0;
        Runnable r1 = new MyThreadPool.connectTask(this.c,s1);
        Runnable r2 = new MyThreadPool.LoadIOTask(this.c,s1);
        final int[] cddd = new int[1];
        cddd[0]=0;
        MyThreadPool.Task t=new MyThreadPool.conncetis(this.c,cddd,latch,s1);
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(t);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int num= s1.mystate;

        return num;
    }


    public void onLoginClicked() {
    int status=isClientConnet();
        if (status==1){      ///  if (user.isInputDataValid())
            setToastMessage(successMessage);

           //// Runnable r1 = new MyThreadPool.sendData(c,1,s1);
            ///pool.execute(r1);
        }
        else{            setToastMessage(errorMessage);}
    }
}