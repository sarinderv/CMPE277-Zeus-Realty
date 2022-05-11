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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cmpe277.project.zeusrealty.MainActivity;
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
        Call<List<LocationAPIResponse>> call = service.getAllProperties();
        call.enqueue(new Callback<List<LocationAPIResponse>>() {
            @Override
            public void onResponse(Call<List<LocationAPIResponse>> call, Response<List<LocationAPIResponse>> response) {
                System.out.println("documents "+response.body().size());
                stackPropertyList(response.body());
                updateProperties();
            }

            @Override
            public void onFailure(Call<List<LocationAPIResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StackProperties current=listProperties.get(i);
                ((MainActivity)getActivity()).openDetails(current);

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
    public void stackPropertyList(List<LocationAPIResponse> propertyList) {
        for (LocationAPIResponse property:
                propertyList) {
            stackProperties = new StackProperties();
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
            listProperties.add(stackProperties);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}