package com.example.rafproject2.viewmodel;

import android.app.Application;

import com.example.rafproject2.model.UserResponse;
import com.example.rafproject2.repository.AuthRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SplashViewModel extends AndroidViewModel {

    private AuthRepository mAuthRepository;


    public SplashViewModel(@NonNull Application application) {
        super(application);
        mAuthRepository = new AuthRepository(application);
    }

    public LiveData<UserResponse> getLoggedInUserLiveData() {
        return mAuthRepository.getUser();
    }
}
