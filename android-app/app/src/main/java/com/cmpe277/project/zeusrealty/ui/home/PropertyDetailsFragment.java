package com.cmpe277.project.zeusrealty.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cmpe277.project.zeusrealty.ArFragment;
import com.cmpe277.project.zeusrealty.MainActivity;
import com.cmpe277.project.zeusrealty.R;


public class PropertyDetailsFragment extends Fragment {
    // TODO: Rename and change types of parameters


    public PropertyDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArFragment newInstance(String param1, String param2) {
        ArFragment fragment = new ArFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container != null) {
            container.clearDisappearingChildren();
        }
        View root =inflater.inflate(R.layout.fragment_property_details, container, false);
        StackProperties props=((MainActivity)getActivity()).getSelProperties();
        return root;
    }
}

