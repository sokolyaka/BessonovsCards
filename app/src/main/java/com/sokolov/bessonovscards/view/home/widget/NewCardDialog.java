package com.sokolov.bessonovscards.view.home.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.sokolov.bessonovscards.R;


public class NewCardDialog extends DialogFragment {
    private OnAddListener onAddListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v =
                getActivity()
                        .getLayoutInflater()
                        .inflate(R.layout.home_dialog_new_card, null);
        return
                new AlertDialog.Builder(getActivity())
                        .setView(v)
                        .setPositiveButton(
                                R.string.add_new_card,
                                (dialog, id) ->
                                        onAddListener
                                                .onClick(
                                                        ((EditText) v.findViewById(R.id.text)).getText(),
                                                        ((EditText) v.findViewById(R.id.translate)).getText()))
                        .setNegativeButton(
                                R.string.cancel,
                                (dialog, id) ->
                                        NewCardDialog.this.getDialog().cancel())
                        .create();
    }

    public interface OnAddListener {
        void onClick(CharSequence text, CharSequence translate);
    }

    public void show(FragmentManager manager, String tag, OnAddListener onAddListener) {
        this.onAddListener = onAddListener;
        super.show(manager, tag);
    }
}
