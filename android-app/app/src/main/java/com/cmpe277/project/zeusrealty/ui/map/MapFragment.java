package com.cmpe277.project.zeusrealty.ui.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.cmpe277.project.zeusrealty.InfoWindowActivity;
import com.cmpe277.project.zeusrealty.MainActivity;
import com.cmpe277.project.zeusrealty.R;
import com.cmpe277.project.zeusrealty.databinding.FragmentMapBinding;
import com.cmpe277.project.zeusrealty.ui.dialog.InfoBottomDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment  implements LocationListener {

    private FragmentMapBinding binding;
    private MapView mMapView;
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private String provider;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MapViewModel mapViewModel =
                new ViewModelProvider(this).get(MapViewModel.class);

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mMapView = (MapView) root.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                System.out.println("Map ready!!!!");
                googleMap = mMap;
                boolean success = googleMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                getActivity().getApplicationContext(), R.raw.style_json));
                setupLocation();
             }
        });


        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).hideFragment();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity)getActivity()).hideFragment();

        binding = null;
    }
    public void setupLocation(){
        locationManager = (LocationManager) getActivity().getSystemService(getContext().LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            System.out.println("No Location");
            // latituteField.setText("Location not available");
            //longitudeField.setText("Location not available");
        }
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(googleMap!=null){
            googleMap.setMyLocationEnabled(true);
            LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
            /*   googleMap.addMarker(new MarkerOptions().position(sydney).title("Title").snippet("Marker Description"));
             */ // For zooming functionality
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
            {
                @Override
                public void onMapClick(LatLng latLng)
                {
                    System.out.println("Map Clicked");

                    //Code for marker deselect goes here
                    ((MainActivity)getActivity()).hideFragment();

                }
            });
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
            {
                @Override
                public boolean onMarkerClick(Marker marker)
                {
                    System.out.println("Marker Clicked");
                    ((MainActivity)getActivity()).showMessageInfo("Test Title Marker");
                    return true;
                }
            });

            CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            createMarkers(myLocation);


        }
    }
    public void createMarkers(LatLng myLocation){
        googleMap.addMarker(new MarkerOptions()
                .position(myLocation).title("Home"));

    }
}