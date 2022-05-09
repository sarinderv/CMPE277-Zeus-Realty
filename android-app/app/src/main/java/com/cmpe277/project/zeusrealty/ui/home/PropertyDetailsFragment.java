package com.cmpe277.project.zeusrealty.ui.home;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cmpe277.project.zeusrealty.R;
import com.cmpe277.project.zeusrealty.databinding.FragmentHomeBinding;
import com.cmpe277.project.zeusrealty.databinding.FragmentPropertyDetailsBinding;

import java.util.ArrayList;


public class PropertyDetailsFragment extends Fragment {
    private FragmentPropertyDetailsBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //HomeViewModel homeViewModel =
                //new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentPropertyDetailsBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_property_details, container, false);
        ImageView imageview = (ImageView) root.findViewById(R.id.content_product_detail_recycer_view_cur_image);
        Drawable res = getResources().getDrawable(R.drawable.property);
        imageview.setImageDrawable(res);
        return root;
    }
}

