package com.cmpe277.project.zeusrealty.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cmpe277.project.zeusrealty.R;
import com.cmpe277.project.zeusrealty.model.LocationAPIResponse;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class InfoBottomDialog extends BottomSheetDialogFragment {
    View view;
    public static InfoBottomDialog newInstance() {
        return new InfoBottomDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.activity_info_dialog, container,
                false);

        // get the views and attach the listener

        return view;

    }
    public void setInfo(LocationAPIResponse resp){
        System.out.println("This Fragment "+resp.getName());
        final TextView textView =view.findViewById(R.id.title);
        textView.setText(resp.getName());
        final TextView snippet =view.findViewById(R.id.snippet);
        snippet.setText(resp.getCategory());
    }
}
