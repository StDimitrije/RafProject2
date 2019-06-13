package com.example.rafproject2.viewmodel;

import android.app.Application;

import com.example.rafproject2.model.User;
import com.example.rafproject2.model.UserResponse;
import com.example.rafproject2.repository.AuthRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LogInViewModel extends AndroidViewModel {

    private AuthRepository mAuthRepository;


    public LogInViewModel(@NonNull Application application) {
        super(application);
        mAuthRepository = new AuthRepository(application);
    }


    public void logInUser(String indexId, String name) {
        User user = new User(indexId, name);
        mAuthRepository.storeUser(user);
    }

    public LiveData<UserResponse> getUserStoreLiveData() {
        return mAuthRepository.getUserStoreLiveData();
    }
}
