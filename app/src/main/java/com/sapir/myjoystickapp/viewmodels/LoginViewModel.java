package com.sapir.myjoystickapp.viewmodels;

import android.app.ProgressDialog;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.sapir.myjoystickapp.model.ConnectStatus;
import com.sapir.myjoystickapp.model.MyThreadPool;
import com.sapir.myjoystickapp.model.Client;
import com.sapir.myjoystickapp.BR;
import com.sapir.myjoystickapp.model.clientToServer;
import com.sapir.myjoystickapp.views.MyNewJoystick;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class LoginViewModel extends BaseObservable {
    private final Client client;
    private final clientToServer c;
    ExecutorService pool;
    ConnectStatus s1;
    MyNewJoystick joy1;
    ProgressDialog nDialog;

    @Bindable
    public String toastMessage = null;


    public void setClientIP(String email) {
        client.setIP(email);
        notifyPropertyChanged(BR.clientIP);
    }

    @Bindable
    public String getClientIP() {
        return client.getIP();
    }

    @Bindable
    public String getClientPassword() {
        return client.getPort();
    }

    public void setClientPassword(String password) {
        client.setPort(password);
        notifyPropertyChanged(BR.clientPassword);
    }

    public String getToastMessage() {
        return toastMessage;
    }


    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public void update() {
        ///   Log.d("lvm"," update .server status:"+this.s1.mystate);
        nDialog.dismiss();
        if (this.s1.mystate == 1) {

            String successMessage = "Login was successful";
            setToastMessage(successMessage);

        } else {
            String faMessage = "IP or Port not valid";
            setToastMessage(faMessage);

        }

    }

    public LoginViewModel(MyNewJoystick joyy2, ProgressDialog nDialog2) {

        client = new Client("", "");
        this.c = new clientToServer();
        this.pool = Executors.newFixedThreadPool(1);
        this.s1 = new ConnectStatus(this);
        this.s1.mystate = -1;
        this.joy1 = joyy2;


        this.nDialog = nDialog2;
    }

    //// th from 0 to 1
    public void sendThrottleToServer(int progressChangedValue) {

        double th = progressChangedValue;

        double th2 = th * 0.01;
        Log.d("lvm", " sendThrottleToServer " + th2);
        if (this.s1.mystate == 1) {
            Runnable t = new MyThreadPool.sendTrotthle(this.c, th2, s1);
            pool.execute(t);
        }


    }

    ////rudder from -1 to 1
    public void sendRudderToServer(int progressChangedValue) {
        double ru = progressChangedValue;

        double ru2 = ru * 0.01;
        Log.d("lvm", " RudderToToServer " + ru2);
        if (this.s1.mystate == 1) {
            Runnable t = new MyThreadPool.sendRudder(this.c, ru2, s1);
            pool.execute(t);
        }


    }

    public void afterIPTextChanged(CharSequence s) {

        client.setIP(s.toString());
        c.setIP(s.toString());

    }

    public void afterPortTextChanged(CharSequence s) {
        client.setPort(s.toString());
        String Port = s.toString();
        int myport = Integer.parseInt(Port);
        c.setPort(myport);
    }

    public void connetMyClient() {

        s1.mystate = -1;
        Runnable r1 = new MyThreadPool.connectTask(this.c, s1);
        Runnable r2 = new MyThreadPool.LoadIOTask(this.c, s1);

        pool.execute(r1);
        pool.execute(r2);
    }


    public void sendAlironAndElevtor(double x, double y) {
        if (s1.mystate == 1) {
            Runnable r1 = new MyThreadPool.sendData(this.c, x, y, s1);
            pool.execute(r1);
        }
    }

    public void onLoginClicked() {

        if (this.s1.mystate == 1) {
            return;
        }

        String Message = "waiting for the server...";
        setToastMessage(Message);
        String myip = client.getIP();
        String myport = client.getPort();
        int myport1 = 0;
        if ((!(myport.equals("")))) {
            myport1 = Integer.parseInt(myport);
        }
        if (myip.equals("")) {
            myip = "192.168.1.103";
            myport1 = 5400;

        }
        c.setIP(myip);
        c.setPort(myport1);
        connetMyClient();
        nDialog.show();

    }
}