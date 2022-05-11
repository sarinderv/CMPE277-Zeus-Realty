package com.cmpe277.project.zeusrealty.ui.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cmpe277.project.zeusrealty.MainActivity;
import com.cmpe277.project.zeusrealty.SplashActivity;
import com.cmpe277.project.zeusrealty.data.User;
import com.cmpe277.project.zeusrealty.databinding.FragmentNotificationsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private FirebaseAuth auth;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        auth=FirebaseAuth.getInstance();

        final TextView textView = binding.textView2;
        final TextView tv=binding.textView;
        User user=new User(auth.getCurrentUser().getEmail(),auth.getCurrentUser().getDisplayName());
        textView.setText(user.email);
        tv.setText(user.name);
        final ImageView imgView = binding.imageView2;
        Picasso.get().load(auth.getCurrentUser().getPhotoUrl()).into(imgView);

        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        final Button button= binding.button;
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).logout();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}