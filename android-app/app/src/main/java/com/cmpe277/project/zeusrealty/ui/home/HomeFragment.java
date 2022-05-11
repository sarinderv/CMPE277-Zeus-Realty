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
import com.cmpe277.project.zeusrealty.apiservice.IListingAPI;
import com.cmpe277.project.zeusrealty.client.RetrofitClientInstance;
import com.cmpe277.project.zeusrealty.databinding.FragmentHomeBinding;
import com.cmpe277.project.zeusrealty.model.LocationAPIResponse;
import com.cmpe277.project.zeusrealty.model.PropertyListingAPIResponse;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<StackProperties> listProperties = new ArrayList<>();
    StackProperties stackProperties;


    private PropertyAdapter pAdapter;
    public HomeFragment(){};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ListView lv = (ListView)root.findViewById(R.id.propertyList);
        IListingAPI service = RetrofitClientInstance.getRetrofitInstance().create(IListingAPI.class);
        System.out.println("/listings");
        Call<List<PropertyListingAPIResponse>> call = service.getPropertyListings();
        call.enqueue(new Callback<List<PropertyListingAPIResponse>>() {
            @Override
            public void onResponse(Call<List<PropertyListingAPIResponse>> call, Response<List<PropertyListingAPIResponse>> response) {
                System.out.println("documents "+response.body().size());
                stackPropertyList(response.body());
            }

            @Override
            public void onFailure(Call<List<PropertyListingAPIResponse>> call, Throwable t) {
                System.out.println("error "+t.getMessage());
            }
        });
        //ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,listProperty);
        //lv.setAdapter(arrayAdapter);
        listProperties.add(stackProperties);

        pAdapter = new PropertyAdapter(getActivity(), -1, listProperties);
        lv.setAdapter(pAdapter);

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

    public void stackPropertyList(List<PropertyListingAPIResponse> propertyList) {
        for (PropertyListingAPIResponse property:
                propertyList) {
            stackProperties = new StackProperties();
            stackProperties.setCountry("US");
            stackProperties.setArea(property.getTotal_area());
            stackProperties.setBuilding("322");
            stackProperties.setCity("San Jose");
            stackProperties.setDesignation(property.getCategory());
            stackProperties.setDescription(property.getDescription());
            stackProperties.setImgUrl("img 1");
            stackProperties.setCount("2");
            stackProperties.setLivingArea(property.getLiving_area());
            stackProperties.setLatitude(property.getX_coordinate());
            stackProperties.setLongitude(property.getY_coordinate());
            stackProperties.setPrice(property.getPrice());
            stackProperties.setAbout("Property 1");
            stackProperties.setReference("Reference");
            listProperties.add(stackProperties);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}