package com.journaldev.myjoystickapp.viewmodels;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class MySpinnerDialog extends DialogFragment {
    ProgressDialog _dialog;
    public MySpinnerDialog() {
        // use empty constructors. If something is needed use onCreate's
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

     _dialog = new ProgressDialog(getActivity());
        this.setStyle(STYLE_NO_TITLE, getTheme()); // You can use styles or inflate a view
        _dialog.setMessage("Spinning.."); // set your messages if not inflated from XML

        _dialog.setCancelable(false);

        return _dialog;
    }
}