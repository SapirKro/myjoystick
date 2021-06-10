package com.sapir.myjoystickapp.views;


import android.util.Log;

import com.sapir.myjoystickapp.model.ConnectStatus;
import com.sapir.myjoystickapp.model.MyThreadPool;
import com.sapir.myjoystickapp.model.clientToServer;

import java.util.concurrent.ExecutorService;


import static java.lang.Math.sin;
import static java.lang.Math.cos;


public class TheJoystick  {
    clientToServer c;
    ////JoystickView joystick;
    MyNewJoystick j1;
    double Ymove;
    double Xmove;

    public TheJoystick(clientToServer c1, MyNewJoystick j2) {

     //////   this.joystic//k=joystick1;
        this.c=c1;
        this.Xmove=0;
        this.Ymove=0;
        this.j1=j2;
    }

/*
    public void doInChange(ExecutorService pool, ConnectStatus s1){


       /// MyNewJoystick.OnMoveListener l1=(angle, strength) -> {

            ///angle=45;
            ///  strength=100;
            double move=0.01;
            double s=strength;
            move = s*0.01;
            Log.d("TheJoystick","angle"+angle);
            Log.d("TheJoystick","strength"+strength);
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
            Log.d("TheJoystick","Ymove"+Ymove);
            Log.d("TheJoystick","Xmove"+Xmove);
            this.Xmove=myXmove;
            this.Ymove=myYmove;
            if(s1.mystate==1){
                Runnable r1 = new MyThreadPool.sendData(c,this.Xmove,this.Ymove,s1);

                pool.execute(r1);}

        };

        this.j1.setOnMoveListener(l1,100);


     /*   JoystickView.OnMoveListener ll=(angle, strength) -> {

            ///angle=45;
            ///  strength=100;
            double move=0.01;
            double s=strength;
            move = s*0.01;
            double myYmove=sin(angle)*move;
            double myXmove=cos(angle)*move;
            Log.d("TheJoystick","Ymove"+Ymove);
            Log.d("TheJoystick","Xmove"+Xmove);
            this.Xmove=myXmove;
            this.Ymove=myYmove;
            if(s1.mystate==1){
                Runnable r1 = new MyThreadPool.sendData(c,this.Xmove,this.Ymove,s1);

           pool.execute(r1);}

        };
       //// this.joystick.setOnMoveListener(ll,100);
    }*/
}
