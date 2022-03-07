package com.google.android.gms.location.sample.locationaddress;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MobileArrayAdapter extends ArrayAdapter<Address> {
    private final Context context;
    private final List<Address> values;

    public MobileArrayAdapter(Context context, List<Address> values) {
        super(context, R.layout.main_activity, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        TextView textView = (TextView) convertView.findViewById(R.id.label);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.logo);
        textView.setText(values.get(position).getAddressLine(0));

        // Change icon based on name
        Address a = values.get(position);
        String s = a.toString();

        if (s.equals("WindowsMobile")) {
            imageView.setImageResource(R.drawable.android_logo);
        } else if (s.equals("iOS")) {
            imageView.setImageResource(R.drawable.android_logo);
        } else if (s.equals("Blackberry")) {
            imageView.setImageResource(R.drawable.android_logo);
        } else {
            imageView.setImageResource(R.drawable.android_logo);
        }

        return convertView;
    }
}