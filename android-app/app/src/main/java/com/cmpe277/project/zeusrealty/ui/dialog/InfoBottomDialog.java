package com.cmpe277.project.zeusrealty.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cmpe277.project.zeusrealty.MainActivity;
import com.cmpe277.project.zeusrealty.R;
import com.cmpe277.project.zeusrealty.model.LocationAPIResponse;
import com.cmpe277.project.zeusrealty.ui.home.StackProperties;
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
        final Button btn =view.findViewById(R.id.btn_goto);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationAPIResponse property=resp;
                StackProperties stackProperties = new StackProperties();
                stackProperties.setAddress(property.getAddress());
                stackProperties.setImageURL(property.getImageURL());
                stackProperties.setAbout(property.getAbout());
                stackProperties.setCategory(property.getCategory());
                stackProperties.setPrice(property.getPrice());
                stackProperties.setCount(property.getCount());
                stackProperties.setName(property.getName());
                stackProperties.setLiving_area(property.getLiving_area());
                stackProperties.setTotal_area(property.getTotal_area());
                stackProperties.setCountry(property.getCountry());
                ((MainActivity)getActivity()).switchToFragmentView(stackProperties);
            }
        });
        final Button btnae =view.findViewById(R.id.btn_goto_ar);
        btnae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).switchToArFragmentView();
            }
        });

    }
}
