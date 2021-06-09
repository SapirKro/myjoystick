package com.sapir.myjoystickapp.viewmodels;

import android.app.ProgressDialog;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.sapir.myjoystickapp.model.ConnectStatus;
import com.sapir.myjoystickapp.model.MyThreadPool;
import com.sapir.myjoystickapp.model.User;
import com.sapir.myjoystickapp.BR;
import com.sapir.myjoystickapp.model.clientToServer;
import com.sapir.myjoystickapp.views.MyNewJoystick;
import com.sapir.myjoystickapp.views.TheJoystick;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class LoginViewModel extends BaseObservable {
    private final User user;
    private final clientToServer c;
    ExecutorService pool;
TheJoystick joy;
    ConnectStatus s1;
    MyNewJoystick joy1;
    ProgressDialog nDialog;

    @Bindable
    public String toastMessage = null;


    public String getToastMessage() {
        return toastMessage;
    }


    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public LoginViewModel(MyNewJoystick joyy2, ProgressDialog nDialog2) {

        user = new User("", "");
       this.c= new clientToServer();
       this.pool= Executors.newFixedThreadPool(1);
      this.s1=new ConnectStatus();
      this.s1.mystate=-1;
this.joy1=joyy2;
       joy=new TheJoystick(this.c,joyy2);
        joy.doInChange(this.pool,this.s1);
this.nDialog=nDialog2;
    }
////change th from 0 to 1
    public void sendThrottleToServer(  int progressChangedValue) {

        double th=progressChangedValue;

        double th2=th*0.01;
        Log.d("lvm"," sendThrottleToServer "+th2);
        if(this.s1.mystate==1){
            Runnable t=new MyThreadPool.sendTrotthle(this.c,th2,s1);
        pool.execute(t);}


    }
    ////change rudder from -1 to 1
    public void sendRudderToServer(  int progressChangedValue) {
        double ru=progressChangedValue;

               double ru2=ru*0.01;
               ///double value =ru2 -1;
        Log.d("lvm"," RudderToToServer "+ru2);
        if(this.s1.mystate==1){
            Runnable t=new MyThreadPool.sendRudder(this.c,ru2,s1);
            pool.execute(t);}


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

    public ConnectStatus isClientConnet( ) {
        final CountDownLatch latch = new CountDownLatch(1);
        s1.mystate=-1;
        Runnable r1 = new MyThreadPool.connectTask(this.c,s1);
        Runnable r2 = new MyThreadPool.LoadIOTask(this.c,s1);

        MyThreadPool.Task t=new MyThreadPool.conncetis(this.c,latch,s1);
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(t);
      try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      ///  int num= s1.mystate;

        return s1;
    }


    public void onLoginClicked() {
        if(this.s1.mystate==1){
            return;
        }
        String Message = "waiting for the server...";
        setToastMessage(Message);

        int status=0;
        ConnectStatus x= new  ConnectStatus();
        nDialog.show();
        x.mystate=-1;
        x=isClientConnet();

        while(x.mystate==-1){
    ///    nDialog.show();
  /// status=isClientConnet();

        Log.d("ss","dddr ");
       //// nDialog.dismiss();
/*
   while(x.mystate==-1){

       try {
           nDialog.show();
           Thread.sleep(5000);
           nDialog.dismiss();

       } catch (InterruptedException e) {
           e.printStackTrace();
       }

   }

*/

 ///
        ///    nDialog.dismiss();
        if (status==1){
            nDialog.dismiss();///  if (user.isInputDataValid())
            String successMessage = "Login was successful";
            setToastMessage(successMessage);
            return;
           //// Runnable r1 = new MyThreadPool.sendData(c,1,s1);
            ///pool.execute(r1);
        }
        if (status==0){
            nDialog.dismiss();
            String errorMessage = "IP or Port not valid";
            setToastMessage(errorMessage);
            return;
        }
          ///
    }

}}