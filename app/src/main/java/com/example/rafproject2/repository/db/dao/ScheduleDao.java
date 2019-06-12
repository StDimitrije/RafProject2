package com.example.rafproject2.repository.db.dao;

import com.example.rafproject2.repository.db.entity.ScheduleEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface ScheduleDao {

    void insertScheduleToDb(List<ScheduleEntity> scheduleEntityList);

    LiveData<List<ScheduleEntity>> getCompleteSchedule();

    LiveData<List<ScheduleEntity>> getFilteredSchedule(String subject, String teacher, String day, String group);
}
