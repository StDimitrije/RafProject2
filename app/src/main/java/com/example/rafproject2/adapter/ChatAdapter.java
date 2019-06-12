package com.example.rafproject2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafproject2.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_chat_list_item, parent, false);

        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
