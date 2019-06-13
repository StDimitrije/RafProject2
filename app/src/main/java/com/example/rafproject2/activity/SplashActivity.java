package com.example.rafproject2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.rafproject2.R;
import com.example.rafproject2.model.UserResponse;
import com.example.rafproject2.viewmodel.SplashViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class SplashActivity extends AppCompatActivity {

//    private static final int DELAY_MILLIS = 1500;
    private static final String TAG = "SplashActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_splash);
//        startLoginActivity();
        initViewModel();


    }

    private void initViewModel() {
        SplashViewModel viewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        viewModel.getLoggedInUserLiveData().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                if (userResponse.isSuccessful()) {
                    Log.e(TAG, "onChanged: user is logged in " + userResponse.getUser().toString() + " start main activity");
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e(TAG, "onChanged: user is not logged in, start LogIn activity");
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


//    private void startLoginActivity() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent  = new Intent(SplashActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },DELAY_MILLIS);
//    }
}
