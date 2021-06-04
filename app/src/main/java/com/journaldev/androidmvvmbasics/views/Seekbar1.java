package com.journaldev.androidmvvmbasics.views;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.journaldev.androidmvvmbasics.R;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Button;
import android.widget.Toast;
public class Seekbar1 extends AppCompatActivity {
Context cc;
    SeekBar customSeekBar;


public Seekbar1(Context c1){
    this.cc=c1;
}


    protected void doCahnge() {




        // initiate  views
        customSeekBar =(SeekBar)findViewById(R.id.customSeekBar);
        // perform seek bar change listener event used for getting the progress value
        customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                Log.d("Seekbar1"," progressChangedValue "+progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}
