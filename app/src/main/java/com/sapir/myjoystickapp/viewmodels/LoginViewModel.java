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


    public void setUserEmail(String email) {
        user.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    public String getUserEmail() {
        return user.getEmail();
    }

    @Bindable
    public String getUserPassword() {
        return user.getPort();
    }

    public void setUserPassword(String password) {
        user.setPort(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public String getToastMessage() {
        return toastMessage;
    }


    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
      notifyPropertyChanged(BR.toastMessage);
    }
    public void update(){
        Log.d("lvm"," update .server status:"+this.s1.mystate);
        nDialog.dismiss();
        if (this.s1.mystate==1){
            ///     nDialog.dismiss();///  if (user.isInputDataValid())
            String successMessage = "Login was successful";
            setToastMessage(successMessage);



        }else{
          String faMessage = "IP or Port not valid";
            setToastMessage(faMessage);
           /// TopMessageManager.showSuccess("ASDADA");
        }

    }
    public LoginViewModel(MyNewJoystick joyy2, ProgressDialog nDialog2) {

        user = new User("", "");
       this.c= new clientToServer();
       this.pool= Executors.newFixedThreadPool(1);
      this.s1=new ConnectStatus(this);
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

    public void isClientConnet( ) {
      ///  final CountDownLatch latch = new CountDownLatch(1);
        s1.mystate=-1;
        Runnable r1 = new MyThreadPool.connectTask(this.c,s1);
        Runnable r2 = new MyThreadPool.LoadIOTask(this.c,s1);

      ///  MyThreadPool.Task t=new MyThreadPool.conncetis(this.c,latch,s1);
        pool.execute(r1);
        pool.execute(r2);
      ///  pool.execute(t);
     /* try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int num= s1.mystate;*/


    }

  /*  public void testrunnable() {
        MyThreadPool.RunnableExample[] randomNumberTasks = new MyThreadPool.RunnableExample[5];

        for (int i = 0; i < 5; i++)
        {
            randomNumberTasks[i] = new MyThreadPool.RunnableExample(this.c,this.s1);

            pool.execute(randomNumberTasks[i]);

        }

    }*/

    public void sendAlironAndElevtor(double x,double y){
        if(s1.mystate==1){
            Runnable r1 = new MyThreadPool.sendData(this.c,x,y,s1);

            pool.execute(r1);}
    }
    public void onLoginClicked() {

        if (this.s1.mystate == 1) {
            return;
        }
     ///   testrunnable();
        String Message = "waiting for the server...";
        setToastMessage(Message);
String myip=user.getEmail();
String myport=user.getPort();
        int myport1=0;
if((!(myport.equals("")))){
myport1=Integer.parseInt(myport);}
if(myip.equals("")){
    myip="192.168.1.103";
    myport1=5400;

}
        c.setIP(myip);
c.setPort(myport1);

       isClientConnet();
        nDialog.show();

      /* int status=0;
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
    }*/
    }
}