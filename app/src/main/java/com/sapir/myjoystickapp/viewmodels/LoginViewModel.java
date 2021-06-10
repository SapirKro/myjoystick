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


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class LoginViewModel extends BaseObservable {
    private final User user;
    private final clientToServer c;
    ExecutorService pool;
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

    public LoginViewModel(MyNewJoystick joyy2, ProgressDialog nDialog2,String ip) {

        user = new User("", "");
       this.c= new clientToServer(ip);
       this.pool= Executors.newFixedThreadPool(1);
      this.s1=new ConnectStatus();
      this.s1.mystate=-1;
this.joy1=joyy2;
       /// doInChange();
        this.joy1.addClientandPOOl(this.pool,this.s1,this.c);
      ///  joy1.doInChange(this.pool,this.s1);
this.nDialog=nDialog2;
    }
/*
    public void doInChange() {
        MyNewJoystick.OnMoveListener l1=(angle, strength) -> {

            ///angle=45;
            ///  strength=100;
            double move=0.01;
            double s=strength;
            move = s*0.01;
            Log.d("TheJoystick","angle "+angle);
            Log.d("TheJoystick","strength "+strength);
            double myYmove=sin(angle)*move;
            double myXmove=cos(angle)*move;
            if((angle<180)&&(angle>0) ){
                if (myYmove<0){
                    myYmove=(-1)*myYmove;
                }
            }
            if((angle<360)&&(angle>180) ){
                if (myYmove>0){
                    myYmove=(-1)*myYmove;
                }
            }
            if((angle<90)&&(angle>0) ){
                if (myXmove<0){
                    myXmove=(-1)*myXmove;
                }
            }
            if((angle<360)&&(angle>270) ){
                if (myXmove<0){
                    myXmove=(-1)*myXmove;
                }
            }
            if((angle>90)&&(angle<270) ){
                if (myXmove>0){
                    myXmove=(-1)*myXmove;
                }
            }
            Log.d("TheJoystick","Ymove "+myYmove);
            Log.d("TheJoystick","Xmove "+myXmove);

            if(s1.mystate==1){
                Runnable r1 = new MyThreadPool.sendData(c,myXmove,myYmove,s1);

                pool.execute(r1);}

        };

        this.joy1.setOnMoveListener(l1,100);

    }
*/
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

    public int isClientConnet( ) {
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

        int num= s1.mystate;

        return num;
    }


    public void onLoginClicked() {
        if(this.s1.mystate==1){
            return;
        }
        String Message = "waiting for the server...";
        setToastMessage(Message);

        int status=0;
        ConnectStatus x= new  ConnectStatus();
       //// nDialog.show();
       /// x.mystate=-1;
      int ss=  isClientConnet();

       /* while(x.mystate==-1){
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
        if (ss==1){
       ///     nDialog.dismiss();///  if (user.isInputDataValid())
            String successMessage = "Login was successful";
            setToastMessage(successMessage);
            return;
           //// Runnable r1 = new MyThreadPool.sendData(c,1,s1);
            ///pool.execute(r1);
        }
        if (ss==0){
          ///  nDialog.dismiss();
            String errorMessage = "IP or Port not valid";
            setToastMessage(errorMessage);
            return;
        }
          ///
    }

}