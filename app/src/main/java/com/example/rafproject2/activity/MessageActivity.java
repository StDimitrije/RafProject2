package com.example.rafproject2.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafproject2.R;
import com.example.rafproject2.adapter.MessageAdapter;
import com.example.rafproject2.model.Chat;
import com.example.rafproject2.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {
    private CircleImageView mCircleImageView;
    private TextView mUsernameTv;
    private RecyclerView recyclerView;
    private EditText mMessageEt;
    private ImageButton mSendBtn;

    private MessageAdapter mMessageAdapter;
    private List<Chat> mChat;

    private String mUserId;
    private String sender;

    private DatabaseReference dbReference;
    private Intent intent;

    private static final String USERNAME_KEY = "userNameKey";
    private static final String INDEX_ID_KEY = "indexIdKey";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        init();

    }

    private void init() {
        mUsernameTv = findViewById(R.id.chat_username_tv);
        mCircleImageView = findViewById(R.id.chat_profile_img);
        mMessageEt = findViewById(R.id.chat_textMessage_et);
        recyclerView = findViewById(R.id.chat_messages_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        String packageName = getPackageName();
        SharedPreferences sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE);
        sender = sharedPreferences.getString(INDEX_ID_KEY, null);


        mSendBtn = findViewById(R.id.chat_send_btn);
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = mMessageEt.getText().toString();
                if (!msg.equals("")){
                    sendMessage(sender,mUserId,msg);
                }else{
                    Toast.makeText(MessageActivity.this, "Empty message field!", Toast.LENGTH_SHORT).show();
                }
                mMessageEt.setText("");
            }
        });

        intent = getIntent();
        mUserId = intent.getStringExtra("userId");

        initDb();

    }

    private void initDb() {


        dbReference = FirebaseDatabase.getInstance().getReference("users").child(mUserId);
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                String key = dataSnapshot.getKey();
                mUsernameTv.setText(user.getName());

                readMessage(sender,mUserId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private  void sendMessage(String sender, String receiver, String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);

        reference.child("Chats").push().setValue(hashMap);

    }

    private void readMessage(String myId, String userId){
        mChat = new ArrayList<>();
        dbReference = FirebaseDatabase.getInstance().getReference("Chats");
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren() ){
                    Chat chat = snapshot.getValue(Chat.class);
//                    if(chat.getReceiver().equals(myId) && chat.getSender().equals(userId) ||
//                    chat.getReceiver().equals(userId) && chat.getSender().equals(myId)){
                        mChat.add(chat);
//                    }
                    mMessageAdapter = new MessageAdapter(MessageActivity.this, mChat);
                    recyclerView.setAdapter(mMessageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
