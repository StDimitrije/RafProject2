package com.example.rafproject2.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rafproject2.R;
import com.example.rafproject2.adapter.ChatAdapter;
import com.example.rafproject2.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChatFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ChatAdapter mChatAdapter;
    private List<User> mUserList;
    private DatabaseReference dbReferance;
    private ChildEventListener childEventListener;
    private ValueEventListener valueEventListener;
    private static final String TAG = "ChatFragment";


    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_chat,container,false);

        mChatAdapter = new ChatAdapter(getContext());
        mRecyclerView = view.findViewById(R.id.fragment_chat_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mChatAdapter);
        loadUsers();

        return view;
    }

    private void loadUsers() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        dbReferance = firebaseDatabase.getReference().child("users");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUserList = new ArrayList<>();

                if (dataSnapshot.getValue() == null) {
                    return;
                }

                for (DataSnapshot childDataSnapshot:dataSnapshot.getChildren()) {
                    User user = childDataSnapshot.getValue(User.class);
                    // Since each employee is identified by key in the realtime db,
                    // we get it as a part od data snapshot object, and not inside of object itself.
                    // We want to add it to the object when we get employee objects from the server.
                    // Why? Because we have to uniqly identify objects in DiffUtil, and we want to
                    // use key to get reference to the object when we want to delete it from the db.
                    String key = childDataSnapshot.getKey();
                    user.setId(key);
                    mUserList.add(user);
                }
                mChatAdapter.setData(mUserList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        dbReferance.addValueEventListener(valueEventListener);

         childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Log.e(TAG, "onChildAdded: " + dataSnapshot.toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Log.e(TAG, "onChildChanged: " + dataSnapshot.toString());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "onChildRemoved: " + dataSnapshot.toString());

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dbReferance.addChildEventListener(childEventListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dbReferance.removeEventListener(valueEventListener);
        dbReferance.removeEventListener(childEventListener);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
