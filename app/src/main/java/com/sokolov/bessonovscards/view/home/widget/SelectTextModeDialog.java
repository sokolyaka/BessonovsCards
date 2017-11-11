package com.sokolov.bessonovscards.view.home.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.sokolov.bessonovscards.R;


public class SelectTextModeDialog extends DialogFragment {
    private OnTextModeSelectedListener onTextModeSelectedListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView =
                getActivity()
                        .getLayoutInflater()
                        .inflate(R.layout.home_dialog_select_text_mode, null);

        rootView
                .findViewById(R.id.tv_text)
                .setOnClickListener(v1 ->
                        onTextModeSelectedListener
                                .onClick("textMode"));

        rootView
                .findViewById(R.id.tv_translate)
                .setOnClickListener(v1 ->
                        onTextModeSelectedListener
                                .onClick("translateMode"));

        return
                new AlertDialog.Builder(getActivity())
                        .setView(rootView)
                        .create();
    }

    public interface OnTextModeSelectedListener {
        void onClick(CharSequence textMode);
    }

    public void show(FragmentManager manager, String tag, OnTextModeSelectedListener onTextModeSelectedListener) {
        this.onTextModeSelectedListener = onTextModeSelectedListener;
        super.show(manager, tag);
    }
}
