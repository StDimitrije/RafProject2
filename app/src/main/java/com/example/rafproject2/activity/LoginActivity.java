package com.example.rafproject2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rafproject2.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private String indexPattern = "[Rr][MmNn]-[0-9][0-9]-[0-9][0-9]";
    private EditText mIndexEt;
    private EditText mNameEt;
    private Button mLoginBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        initUi();
    }

    private void initUi() {
        mIndexEt = findViewById(R.id.et_index);
        mNameEt = findViewById(R.id.et_name);
        mLoginBtn = findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name =mNameEt.getText().toString();
                String index = mIndexEt.getText().toString();

//                Pattern pattern = Pattern.compile(indexPattern);
//                Matcher matcher = pattern.matcher(index);
//                boolean match = matcher.matches();

//                if(name.trim().isEmpty() || !match){
//                    Toast.makeText(LoginActivity.this, "Neispravan unos!", Toast.LENGTH_SHORT).show();
//                }else{
//                    //login user with usage of loginviewmodel
//                }

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });
    }
}
