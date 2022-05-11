package com.cmpe277.project.zeusrealty.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.cmpe277.project.zeusrealty.ArFragment;
import com.cmpe277.project.zeusrealty.MainActivity;
import com.cmpe277.project.zeusrealty.R;
import com.cmpe277.project.zeusrealty.databinding.FragmentNotificationsBinding;
import com.cmpe277.project.zeusrealty.databinding.FragmentPropertyDetailsBinding;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class PropertyDetailsFragment extends Fragment {
    // TODO: Rename and change types of parameters
FragmentPropertyDetailsBinding binding;

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
    public static PropertyDetailsFragment newInstance(String param1, String param2) {
        PropertyDetailsFragment fragment = new PropertyDetailsFragment();
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
        binding = FragmentPropertyDetailsBinding.inflate(inflater, container, false);

        View root =inflater.inflate(R.layout.fragment_property_details, container, false);
        StackProperties props=((MainActivity)getActivity()).getSelProperties();
       TextView title = (TextView)root.findViewById(R.id.txt_detail_title);
       TextView about = (TextView)root.findViewById(R.id.txt_detail_description);
       TextView price = (TextView)root.findViewById(R.id.txt_detail_price);
       TextView area = (TextView)root.findViewById(R.id.txt_detail_total_area_top);
       //TextView category = (TextView)root.findViewById(R.id.txt_detail_type);
       TextView count = (TextView)root.findViewById(R.id.txt_detail_nb_rooms);
        TextView totalArea = (TextView)root.findViewById(R.id.txt_detail_total_area);
        TextView description = (TextView)root.findViewById(R.id.txt_detail_type);
        TextView livingArea = (TextView)root.findViewById(R.id.txt_detail_living_area);
        TextView address = (TextView)root.findViewById(R.id.txt_address);
        TextView country = (TextView)root.findViewById(R.id.txt_country);
        ImageView imageUrl = (ImageView) root.findViewById(R.id.iconImg1);

        Picasso.get().load(props.getImageURL()).into(imageUrl);


        title.setText(props.getName());
       about.setText(props.getAbout());
       price.setText("$"+props.getPrice());
       totalArea.setText(props.getTotal_area()+"sq.ft");
       livingArea.setText(props.getLiving_area()+"sq.ft");
       count.setText(props.getCount());
       address.setText(props.getAddress());
       //category.setText(props.getCategory());
       country.setText(props.getCountry());
       area.setText(props.getTotal_area()+"sq.ft");
       description.setText(props.getCategory());

       return root;
    }
}

