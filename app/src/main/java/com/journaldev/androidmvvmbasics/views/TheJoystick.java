package com.journaldev.androidmvvmbasics.views;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.journaldev.androidmvvmbasics.R;
import com.journaldev.androidmvvmbasics.model.ConnectStatus;
import com.journaldev.androidmvvmbasics.model.MyThreadPool;
import com.journaldev.androidmvvmbasics.model.clientToServer;

import java.util.concurrent.ExecutorService;

import io.github.controlwear.virtual.joystick.android.JoystickView;

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


    public void doInChange(ExecutorService pool, ConnectStatus s1){


        MyNewJoystick.OnMoveListener l1=(angle, strength) -> {

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

        };*/

       //// this.joystick.setOnMoveListener(ll,100);
    }
}
