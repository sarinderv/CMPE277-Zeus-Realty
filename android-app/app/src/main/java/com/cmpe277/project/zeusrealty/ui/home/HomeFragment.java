package com.cmpe277.project.zeusrealty.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cmpe277.project.zeusrealty.R;
import com.cmpe277.project.zeusrealty.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public HomeFragment(){};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ArrayList<String> listProperty = new ArrayList<>();
        ListView lv = (ListView)root.findViewById(R.id.propertyList);
        listProperty.add("Here's an example");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,listProperty);
        lv.setAdapter(arrayAdapter);

        //View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        final Button button= binding.button;
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                SharedPreferences settings = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = settings.edit();
//                editor.putBoolean("loggedin", false); // set it to false when the user is logged out
//                // Commit the edits!
//                editor.commit();
//                Intent broadcastIntent = new Intent();
//                broadcastIntent.setAction("com.cmpe277.project.intents.ACTION_LOGOUT");
//                getActivity().sendBroadcast(broadcastIntent);
//            }
//        });
         return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}