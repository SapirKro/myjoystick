package com.sapir.myjoystickapp.views;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.sapir.myjoystickapp.R;
import com.sapir.myjoystickapp.databinding.ActivityMainBinding;
import com.sapir.myjoystickapp.viewmodels.LoginViewModel;


public class MainActivity extends AppCompatActivity {
    SeekBar throttleSeekBar;
    SeekBar rudderSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("p", "create");


        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MyNewJoystick joystick1 = findViewById(R.id.MyNewCOOLJoystick);

        ProgressDialog nDialog;

        nDialog = new ProgressDialog(MainActivity.this);
        nDialog.setMessage("Loading..");
        nDialog.setTitle("trying to connect...");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);

        LoginViewModel lvm = new LoginViewModel(joystick1, nDialog);

        MyNewJoystick.onTouchEventListener ll = (x, y) -> {
            Log.d("fromMain", "Ymove " + y);
            Log.d("fromMain", "Xmove " + x);
            lvm.sendAlironAndElevtor(x, y);

        };
        joystick1.setonTouchEventListener(ll);

        throttleSeekBar = findViewById(R.id.thSeekBar);
        // perform seek bar change listener event used for getting the progress value
        throttleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                Log.d("throttleSeekBar", "thr progressChangedValue " + progressChangedValue);
                lvm.sendThrottleToServer(progressChangedValue);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        rudderSeekBar = findViewById(R.id.RudderseekBar);

        rudderSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                Log.d("RudderseekBar", "RudderseekBar progressChangedValue " + progress);
                lvm.sendRudderToServer(progressChangedValue);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        activityMainBinding.setViewModel(lvm);
        activityMainBinding.executePendingBindings();

    }


    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
