package com.example.rafproject2.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafproject2.R;
import com.example.rafproject2.model.Chat;
import com.example.rafproject2.model.User;
import com.example.rafproject2.util.UserDiffCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;
    private static final String USERNAME_KEY = "userNameKey";

private List<Chat> mChat;
private Context mContext;

public MessageAdapter(Context context, List<Chat> chat){
    mChat = chat;
        mContext = context;
        mChat=new ArrayList<>();


        }

@NonNull
@Override
public MessageHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == MSG_TYPE_RIGHT) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.chat_item_right, parent, false);

        return new MessageHolder(view);
    }else{
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.chat_item_left, parent, false);
        return new MessageHolder(view);
    }
}

@Override
public void onBindViewHolder(@NonNull MessageHolder holder, int position) {

    Chat chat = mChat.get(position);
    holder.showMessage.setText(chat.getMessage());
}

@Override
public int getItemCount() {
        return mChat.size();
        }



public class MessageHolder extends RecyclerView.ViewHolder {
    private TextView showMessage;


    public MessageHolder(@NonNull View itemView) {
        super(itemView);
        showMessage = itemView.findViewById(R.id.chat_showMessage_tv);
    }
}

    @Override
    public int getItemViewType(int position) {
        String packageName = mContext.getPackageName();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(packageName, Context.MODE_PRIVATE);
        String sender = sharedPreferences.getString(USERNAME_KEY, null);
        if (mChat.get(position).getSender().equals(sender)){
            return MSG_TYPE_RIGHT;
        }else{
            return  MSG_TYPE_LEFT;
        }
    }
}
