package com.cmpe277.project.zeusrealty.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cmpe277.project.zeusrealty.R;
import com.cmpe277.project.zeusrealty.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<StackProperties> listProperties = new ArrayList<>();
    StackProperties stackProperties = new StackProperties();


    private PropertyAdapter pAdapter;
    ListView lv;
    public HomeFragment(){};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //ArrayList<String> listProperty = new ArrayList<>();
        lv = (ListView)root.findViewById(R.id.propertyList);
        //listProperty.add("Here's an example");

        //ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,listProperty);
        //lv.setAdapter(arrayAdapter);
        stackProperties.setCountry("country1");
        stackProperties.setArea("383 Stockton AVenue");
        stackProperties.setBuilding("322");
        stackProperties.setCity("San Jose");
        stackProperties.setDesignation("Cahill");
        stackProperties.setDescription("desc");
        stackProperties.setImgUrl("img1");
        stackProperties.setCount("2");
        stackProperties.setLivingArea("living area");
        stackProperties.setLatitude("32");
        stackProperties.setLongitude("34");
        stackProperties.setPrice("$3245");
        stackProperties.setAbout("Property 1");
        stackProperties.setReference("Reference");
        listProperties.add(stackProperties);

        stackProperties = new StackProperties();
        stackProperties.setCountry("country2");
        stackProperties.setArea("833 Stockton AVenue");
        stackProperties.setBuilding("Apartment 319");
        stackProperties.setCity("San Jose");
        stackProperties.setDesignation("Morrison");
        stackProperties.setDescription("desc");
        stackProperties.setImgUrl("img1");
        stackProperties.setCount("2");
        stackProperties.setLivingArea("living area");
        stackProperties.setLatitude("32");
        stackProperties.setLongitude("34");
        stackProperties.setPrice("$3245");
        stackProperties.setAbout("Property 2");
        stackProperties.setReference("Reference");
        listProperties.add(stackProperties);

        stackProperties = new StackProperties();
        stackProperties.setCountry("country3");
        stackProperties.setArea("768 Stockton Avenue");
        stackProperties.setBuilding("Apartment 320");
        stackProperties.setCity("San Jose");
        stackProperties.setDesignation("Alameda");
        stackProperties.setDescription("desc 3");
        stackProperties.setImgUrl("img1");
        stackProperties.setCount("2");
        stackProperties.setLivingArea("living area");
        stackProperties.setLatitude("32");
        stackProperties.setLongitude("34");
        stackProperties.setPrice("$3245");
        stackProperties.setAbout("Property 3");
        stackProperties.setReference("Reference");

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onActivityCreated(savedInstanceState);
//        setHasOptionsMenu(true);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu, menuInflater);
        menu.clear();
        menuInflater.inflate(R.menu.my_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.searchMenu);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<StackProperties> results = new ArrayList<>();
                for(StackProperties property:listProperties){
                    if(property.getDesignation().contains(newText)){
                        results.add(property);
                    }
                }
                ((PropertyAdapter)lv.getAdapter()).update(results);
                return false;
            }
        });

    }
}