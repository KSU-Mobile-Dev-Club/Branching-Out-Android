package com.teioh08.branchingout.UI.Main.View.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.teioh08.branchingout.UI.Main.View.Mapper.Listeners;

public class VerifyDialog extends DialogFragment {
    public final static String TAG = VerifyDialog.class.getSimpleName();
    private Listeners.VerifyDialogListener listener;

    public static DialogFragment getNewInstance() {
        VerifyDialog fragment = new VerifyDialog();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Map")
                .setMessage("Would you like to go straight to the map?")
                .setNegativeButton("No", (arg0, arg1) -> {
                    getDialog().dismiss();
                })

                .setPositiveButton("Yes", (arg0, arg1) -> {
                    listener.goToMap();
                    getDialog().dismiss();
                })
                .create();
    }

    @Override
    public void onAttach(Activity activity) {
        if (activity instanceof Listeners.VerifyDialogListener)
            listener = (Listeners.VerifyDialogListener) activity;
        super.onAttach(activity);
    }
}
