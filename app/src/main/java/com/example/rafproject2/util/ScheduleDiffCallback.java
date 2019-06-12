package com.example.rafproject2.util;

import com.example.rafproject2.repository.db.entity.ScheduleEntity;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class ScheduleDiffCallback extends DiffUtil.Callback {

    private List<ScheduleEntity> mOldList;
    private List<ScheduleEntity> mNewList;

    public ScheduleDiffCallback(List<ScheduleEntity> oldList, List<ScheduleEntity> newList){
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
        ScheduleEntity oldEntity = mOldList.get(oldItemPosition);
        ScheduleEntity newEntity = mNewList.get(newItemPosition);
        return oldEntity.getId() == (newEntity.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ScheduleEntity oldEntity = mOldList.get(oldItemPosition);
        ScheduleEntity newEntity = mNewList.get(newItemPosition);
        return oldEntity.getSubject().equals(newEntity.getSubject()) &&
                oldEntity.getTeacher().equals(newEntity.getTeacher()) &&
                oldEntity.getClassRoom().equals(newEntity.getClassRoom()) &&
                oldEntity.getType().equals(newEntity.getType()) &&
                oldEntity.getDay().equals(newEntity.getDay())  &&
                oldEntity.getTime().equals(newEntity.getTime())&&
                oldEntity.getGroups().equals(newEntity.getGroups());
    }
}
