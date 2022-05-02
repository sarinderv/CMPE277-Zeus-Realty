package com.cmpe277.project.zeusrealty.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.cmpe277.project.zeusrealty.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class InfoBottomDialog extends BottomSheetDialogFragment {

    public static InfoBottomDialog newInstance() {
        return new InfoBottomDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_info_dialog, container,
                false);

        // get the views and attach the listener

        return view;

    }
}
