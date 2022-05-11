package com.cmpe277.project.zeusrealty;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.cmpe277.project.zeusrealty.data.LoginRepository;
import com.cmpe277.project.zeusrealty.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    Boolean isLoggedIn;
    static int openapp;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth=FirebaseAuth.getInstance();
    }
    public void initNavigation(){

        if(!alreadyLoggedIn()) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            activityResultLauncher.launch(intent);
        } else {
           openApp();
        }
    }
    public void openApp(){
        System.out.println("open app called"+(++openapp));
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }

    ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                   openApp();
                }
            }
    );
    public void onResume() {

        super.onResume();
        initNavigation();

    }
    public boolean alreadyLoggedIn(){
        FirebaseUser user=mAuth.getCurrentUser();

        if(user!=null){

            System.out.println(user.getEmail());
            this.isLoggedIn=true;
            return true;
        }
        else{
            this.isLoggedIn=true;
           return false;
        }

    }
}