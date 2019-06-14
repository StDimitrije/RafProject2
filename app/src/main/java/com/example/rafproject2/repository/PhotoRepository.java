package com.example.rafproject2.repository;

import android.net.Uri;

import com.example.rafproject2.model.UploadResult;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PhotoRepository {

    private MutableLiveData<UploadResult<Uri>> uploadPhotoLiveData;
    private StorageReference mUploadFolderReference;

    public PhotoRepository(){

        uploadPhotoLiveData = new MutableLiveData<>();

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        mUploadFolderReference = firebaseStorage.getReference().child("uploads");
    }

    public void uploadPhoto(File photoFile) {
        Uri uri = Uri.fromFile(photoFile);
        String fileName = photoFile.getName();

        StorageReference photoReference = mUploadFolderReference.child(fileName);

        photoReference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        UploadResult<Uri> uploadResult = new UploadResult<>(UploadResult.STATUS_SUCCESS);
                        uploadPhotoLiveData.setValue(uploadResult);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        UploadResult<Uri> uploadResult = new UploadResult<>(UploadResult.STATUS_FAILED);
                        uploadPhotoLiveData.setValue(uploadResult);
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        long progress = ((100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount());
                        UploadResult<Uri> uploadResult = new UploadResult<>(UploadResult.STATUS_UPLOADING, progress);
                        uploadPhotoLiveData.setValue(uploadResult);
                    }
                }).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return photoReference.getDownloadUrl();
            }
        }).addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                UploadResult<Uri> uploadResult = new UploadResult<>(UploadResult.STATUS_GOT_URL, uri);
                uploadPhotoLiveData.setValue(uploadResult);
            }
        });
    }

    public LiveData<UploadResult<Uri>> getUploadPhotoLiveData() {
        return uploadPhotoLiveData;
    }
}
