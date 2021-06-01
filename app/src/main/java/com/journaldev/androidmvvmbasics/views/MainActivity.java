package com.journaldev.androidmvvmbasics.views;


import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.journaldev.androidmvvmbasics.model.MyThreadPool;
import com.journaldev.androidmvvmbasics.model.clientToServer;
import com.journaldev.androidmvvmbasics.R;
import com.journaldev.androidmvvmbasics.databinding.ActivityMainBinding;
import com.journaldev.androidmvvmbasics.viewmodels.LoginViewModel;

import io.github.controlwear.virtual.joystick.android.JoystickView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.print("first statement. ");
Log.d("p","create");
        Log.d("p","first statement");
        ///sapir
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new LoginViewModel());
        activityMainBinding.executePendingBindings();
        JoystickView joystick = (JoystickView) findViewById(R.id.joystickView);



        clientToServer c = new clientToServer();
       c.connectToServer();
        /*   c.LoadIO();
        c.sendData(1);
        c.closeClient();*/
      //// myserver.connect();
       /// MyThreadPool tp=new MyThreadPool();
        System.out.print("first statement. ");
       /*try {
            MyThreadPool.Test dd= new MyThreadPool.Test();
            String[] args={"sa"};
            dd.mytes1(args);
            System.out.print("first statement. ");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
