package com.example.rafproject2.util;

import com.example.rafproject2.model.User;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class UserDiffCallback extends DiffUtil.Callback {

    private List<User> mOldList;
    private List<User> mNewList;

    public UserDiffCallback(List<User> oldList, List<User> newList){
        mOldList = oldList;
        mNewList = newList;
    }


    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        User oldUser = mOldList.get(oldItemPosition);
        User newUser = mNewList.get(newItemPosition);
        return  oldUser.getId().equals(newUser.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        User oldUser = mOldList.get(oldItemPosition);
        User newUser = mNewList.get(newItemPosition);
        return oldUser.getName().equals(newUser.getName());
    }
}
