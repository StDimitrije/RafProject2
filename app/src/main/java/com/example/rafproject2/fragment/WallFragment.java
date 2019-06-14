package com.example.rafproject2.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafproject2.R;
import com.example.rafproject2.adapter.WallAdapter;
import com.example.rafproject2.model.UploadResult;
import com.example.rafproject2.viewmodel.WallViewModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class WallFragment extends Fragment {

    private static final int REQUEST_CAMERA_PERMISSION = 222;
    private static final int REQUEST_CAMERA_PHOTO = 333;

    private RecyclerView recyclerView;
    private EditText mMsgEditText;
    private ImageButton mAddImgBtn;
    private ImageButton mSendMsgBtn;
    private TextView mStatusTv;
    private File mFile;

    private WallAdapter mWallAdapter;

    private WallViewModel mWallViewModel;

    public static WallFragment newInstance() {
        return new WallFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_wall,container,false);

        recyclerView = view.findViewById(R.id.wall_recicler_view);

        mMsgEditText = view.findViewById(R.id.wall_message_et);
        mAddImgBtn = view.findViewById(R.id.wall_add_img_btn);
        mAddImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                takePhoto();

            }
        });

        mSendMsgBtn = view.findViewById(R.id.wall_send_message_btn);
        mSendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return view;
    }

    @SuppressLint("MissingPermission")
    private void takePhoto() {
        if (!hasAnyFeature(PackageManager.FEATURE_CAMERA)) {
            showToast("No feature");
            return;
        }

        if (hasPermissions(Manifest.permission.CAMERA)) {

            try {
                mFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoURI = FileProvider.getUriForFile(getContext(), getString(R.string.app_file_provider), mFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(intent, REQUEST_CAMERA_PHOTO);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWallViewModel = ViewModelProviders.of(this).get(WallViewModel.class);
        mWallViewModel.getUploadPhotoLiveData().observe(this,
                new Observer<UploadResult<Uri>>() {
                    @Override
                    public void onChanged(UploadResult<Uri> uploadResult) {
                         if (uploadResult.getUploadStatus() == UploadResult.STATUS_FAILED) {
                            Toast.makeText(getContext(), "Upload failed!", Toast.LENGTH_SHORT).show();
                        } else if (uploadResult.getUploadStatus() == UploadResult.STATUS_SUCCESS) {
                            Toast.makeText(getContext(), "Photo uploaded, you'll get photo url soon!", Toast.LENGTH_SHORT).show();
                        } else if (uploadResult.getUploadStatus() == UploadResult.STATUS_GOT_URL) {
//                            Picasso.get().load(uploadResult.getData()).into();
                             mAddImgBtn.setEnabled(true);
                        }
                    }
                });

    }


    protected boolean hasAnyFeature(String... features){
        for (String feature : features) {
            if (getActivity().getPackageManager().hasSystemFeature(feature)){
                return true;
            }
        }
        return false;
    }

    protected boolean hasPermissions(String... permissions){
        for (String permission : permissions) {
            boolean hasPermission = ContextCompat.checkSelfPermission(getContext(), permission) == PackageManager.PERMISSION_GRANTED;
            if(!hasPermission) {
                return false;
            }
        }
        return true;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
