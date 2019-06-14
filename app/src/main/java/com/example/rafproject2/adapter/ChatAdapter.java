package com.example.rafproject2.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafproject2.R;
import com.example.rafproject2.activity.MessageActivity;
import com.example.rafproject2.model.User;
import com.example.rafproject2.util.UserDiffCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {

    private List<User> mUsersList;
    private Context mContext;

    public ChatAdapter(Context context){
        mContext = context;
        mUsersList=new ArrayList<>();


    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_chat_list_item, parent, false);

        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        User user = mUsersList.get(position);
        holder.mProfileName.setText(user.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userId", user.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    public void setData(List<User> userList){
        UserDiffCallback callback = new UserDiffCallback(mUsersList, userList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mUsersList.clear();
        mUsersList.addAll(userList);
        result.dispatchUpdatesTo(this);

    }

    public class ChatHolder extends RecyclerView.ViewHolder {
        private ImageView mProfileImg;
        private TextView mProfileName;


        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            mProfileImg = itemView.findViewById(R.id.chat_profile_img);
            mProfileName = itemView.findViewById(R.id.chat_profile_name);
        }
    }
}
