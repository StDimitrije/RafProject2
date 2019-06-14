package com.example.rafproject2.viewmodel;

import android.net.Uri;

import com.example.rafproject2.model.UploadResult;
import com.example.rafproject2.repository.PhotoRepository;

import java.io.File;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class WallViewModel extends ViewModel {

    private PhotoRepository mPhotoRepository;

    public WallViewModel(){
        mPhotoRepository = new PhotoRepository();
    }

    public void uploadPhoto(File file) {
        mPhotoRepository.uploadPhoto(file);
    }

    public LiveData<UploadResult<Uri>> getUploadPhotoLiveData() {
        return mPhotoRepository.getUploadPhotoLiveData();
    }


}
