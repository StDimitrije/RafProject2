package com.example.rafproject2.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WallAdapter extends RecyclerView.Adapter<WallAdapter.WallHolder> {

    public WallAdapter(){}

    @NonNull
    @Override
    public WallHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WallHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class WallHolder extends RecyclerView.ViewHolder{
        public WallHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
