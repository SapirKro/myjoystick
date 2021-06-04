package com.journaldev.androidmvvmbasics.views;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.journaldev.androidmvvmbasics.R;
import com.journaldev.androidmvvmbasics.databinding.ActivityMainBinding;
import com.journaldev.androidmvvmbasics.viewmodels.LoginViewModel;

import io.github.controlwear.virtual.joystick.android.JoystickView;


public class MainActivity extends AppCompatActivity {
    SeekBar customSeekBar;
    SeekBar rudderSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.print("first statement. ");
        Log.d("p", "create");
        Log.d("p", "first statement");
        ///sapir
        ///Seekbar1 s=new Seekbar1(MainActivity.this);
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        JoystickView joystick = (JoystickView) findViewById(R.id.joystickView);
        LoginViewModel lvm=new LoginViewModel(joystick);



     ////   setContentView(R.layout.activity_main);
        // initiate  views
        customSeekBar =(SeekBar)findViewById(R.id.customSeekBar);
        // perform seek bar change listener event used for getting the progress value
        customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                Log.d("Seekbar1","thr progressChangedValue "+progressChangedValue);
               lvm.sendThrottleToServer(progressChangedValue);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });

      /// setContentView(R.layout.activity_main);
        // initiate  views
        rudderSeekBar =(SeekBar)findViewById(R.id.RudderseekBar);
        // perform seek bar change listener event used for getting the progress value
        rudderSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                Log.d("RudderseekBar","RudderseekBar progressChangedValue "+progress);
                lvm.sendRudderToServer(progressChangedValue);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "rudderSeekBar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });

        activityMainBinding.setViewModel(lvm);
        activityMainBinding.executePendingBindings();
    }


/*
 TheJoystick jj=new TheJoystick(joystick);
        jj.doInChange();*/
     /*    clientToServer c = new clientToServer();
        Runnable r1 = new MyThreadPool.connectTask(c);
        Runnable r2 = new MyThreadPool.LoadIOTask(c);
        Runnable r3 = new MyThreadPool.sendData(c,1);
        Runnable r4 = new MyThreadPool.CloseTask(c);
        ExecutorService pool = Executors.newFixedThreadPool(1);


        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);*/
        ///    clientToServer c = new clientToServer();
      /// c.connectToServer();
        /*   c.LoadIO();
        c.sendData(1);
        c.closeClient();*/
      //// myserver.connect();
       /// MyThreadPool tp=new MyThreadPool();
        /*
        System.out.print("first statement. ");
      try {
            MyThreadPool.Test dd= new MyThreadPool.Test();
            String[] args={"sa"};
            dd.mytes1(args);
            System.out.print("first statement. ");
        } catch (Exception e) {
            e.printStackTrace();
        }*/



    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
