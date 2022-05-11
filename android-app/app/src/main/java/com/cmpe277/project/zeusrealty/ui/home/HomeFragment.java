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
import com.cmpe277.project.zeusrealty.model.PropertyListingAPIResponseContainer;
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
    ListView lv;

    private PropertyAdapter pAdapter;
    public HomeFragment(){};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        lv = (ListView)root.findViewById(R.id.propertyList);
        IListingAPI service = RetrofitClientInstance.getRetrofitInstance().create(IListingAPI.class);
        System.out.println("/listings");
        Call<PropertyListingAPIResponseContainer> call = service.getPropertyListings();
        call.enqueue(new Callback<PropertyListingAPIResponseContainer>() {
            @Override
            public void onResponse(Call<PropertyListingAPIResponseContainer> call, Response<PropertyListingAPIResponseContainer> response) {
                System.out.println("documents "+response.body().getData().size());
                stackPropertyList(response.body().getData());
                updateProperties();
            }

            @Override
            public void onFailure(Call<PropertyListingAPIResponseContainer> call, Throwable t) {
                t.printStackTrace();
            }
        });
        updateProperties();
        //ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,listProperty);
        //lv.setAdapter(arrayAdapter);


        //View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

         return root;
    }
    public void updateProperties(){
        listProperties.add(stackProperties);

        pAdapter = new PropertyAdapter(getActivity(), -1, listProperties);
        lv.setAdapter(pAdapter);
    }
    public void stackPropertyList(List<PropertyListingAPIResponse> propertyList) {
        for (PropertyListingAPIResponse property:
                propertyList) {
            stackProperties = new StackProperties();
            stackProperties.setCountry("US");
            stackProperties.setArea(property.getTotal_area());
            stackProperties.setBuilding("322");
            stackProperties.setCity("San Jose");
            stackProperties.setDesignation(property.getName());
            stackProperties.setDescription(property.getDescription());
            stackProperties.setImgUrl("https://tripimages.imgix.net/room4/room4.jpg");
            stackProperties.setCount("2");
            stackProperties.setLivingArea(property.getLiving_area());
            stackProperties.setLatitude(property.getX_coordinate());
            stackProperties.setLongitude(property.getY_coordinate());
            stackProperties.setPrice(property.getPrice());
            stackProperties.setAbout(property.getCategory());
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