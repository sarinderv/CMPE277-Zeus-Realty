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
import com.cmpe277.project.zeusrealty.apiservice.IListingAPI;
import com.cmpe277.project.zeusrealty.client.RetrofitClientInstance;
import com.cmpe277.project.zeusrealty.databinding.FragmentMapBinding;
import com.cmpe277.project.zeusrealty.model.LocationAPIResponse;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapFragment extends Fragment implements LocationListener {

    private FragmentMapBinding binding;
    private MapView mMapView;
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private String provider;
    List<LocationAPIResponse> currResult;
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
                googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                    @Override
                    public void onCameraIdle() {
                        LatLng myLocation = googleMap.getCameraPosition().target;
                        LatLng bottomLeft =
                                googleMap.getProjection().getVisibleRegion().nearLeft;

                        LatLng topRight =
                                googleMap.getProjection().getVisibleRegion().farRight;
                        IListingAPI service = RetrofitClientInstance.getRetrofitInstance().create(IListingAPI.class);
                        System.out.println("/locations/bounds?bounds="+bottomLeft.longitude+","+bottomLeft.latitude+","+topRight.longitude+","+topRight.latitude );
                        Call<List<LocationAPIResponse>> call = service.getLocationDetailsInsideRectangle(bottomLeft.longitude+","+bottomLeft.latitude+","+topRight.longitude+","+topRight.latitude );
                        call.enqueue(new Callback<List<LocationAPIResponse>>() {
                            @Override
                            public void onResponse(Call<List<LocationAPIResponse>> call, Response<List<LocationAPIResponse>> response) {
                                System.out.println("documents "+response.body().size());
                                createMarkers(response.body());
                            }

                            @Override
                            public void onFailure(Call<List<LocationAPIResponse>> call, Throwable t) {
                                System.out.println("error "+t.getMessage());
                            }
                        });
                    }
                });

            }
        });

        ((MainActivity) getActivity()).getInfo();
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity) getActivity()).hideFragment();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity) getActivity()).hideFragment();

        binding = null;
    }

    public void setupLocation() {
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
        if (googleMap != null) {
            googleMap.setMyLocationEnabled(true);
            LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
            /*   googleMap.addMarker(new MarkerOptions().position(sydney).title("Title").snippet("Marker Description"));
             */ // For zooming functionality
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    System.out.println("Map Clicked");

                    //Code for marker deselect goes here
                    ((MainActivity) getActivity()).hideFragment();

                }
            });
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    System.out.println("Marker Clicked "+marker.getTitle());
                    LocationAPIResponse resp=currResult.stream().filter(customer -> marker.getTitle().equals(customer.get_id()))
                            .findAny()
                            .orElse(null);
                    ((MainActivity) getActivity()).showMessageInfo(resp);
                    return true;
                }
            });

            CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        }
    }

    public void createMarkers(List<LocationAPIResponse> locations) {
        //fetch markers around my location and put them on map
        googleMap.clear();
        currResult=locations;
        for (LocationAPIResponse location:
             locations) {
            Double[] coordinates=location.getLocation().getCoordinates();
            LatLng loc = new LatLng(coordinates[1],coordinates[0]);
            googleMap.addMarker(new MarkerOptions()
                    .position(loc).title(location.get_id()));
        }

    }
}