package com.cmpe277.project.zeusrealty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cmpe277.project.zeusrealty.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_map, R.id.navigation_notifications, R.id.navigation_nfc)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
     //   NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.cmpe277.project.intents.ACTION_LOGOUT");
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                System.out.println("logout");
                //At this point you should start the login activity and finish this one
                Intent t = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(t);
                finish();
            }
        }, intentFilter);
        isLocationPermissionGranted();
        hideFragment();
        mAuth=FirebaseAuth.getInstance();

    }
    public void hideFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment dFragment = manager.findFragmentById(R.id.card_fragment);
        ft.hide(dFragment);
        ft.commit();
    }
    public void showMessageInfo(String text){
        System.out.println(text);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment dFragment = manager.findFragmentById(R.id.card_fragment);
        ft.show(dFragment);
        ft.commit();
    }
    public void getInfo() {
        FirebaseUser user=mAuth.getCurrentUser();
        System.out.println("Print USer Details "+user.getEmail());
    }
    public void logout(){
        SharedPreferences settings = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("loggedin", false); // set it to false when the user is logged out
        // Commit the edits!
        editor.commit();
        mAuth.signOut();
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("com.cmpe277.project.intents.ACTION_LOGOUT");
        this.sendBroadcast(broadcastIntent);
    }
    private boolean isLocationPermissionGranted() {
      if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
              ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    0
            );
            return true;
        } else {
            return  true;
        }
    }

}