package com.example.rafproject2.repository.db.dao;

import com.example.rafproject2.repository.db.entity.ScheduleEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertScheduleToDb(List<ScheduleEntity> scheduleEntityList);

    @Query("SELECT * FROM schedule")
    LiveData<List<ScheduleEntity>> getCompleteSchedule();

    @Query("SELECT * FROM schedule " +
            "WHERE subject LIKE :subject || '%' " +
            "AND teacher LIKE :teacher || '%'" +
            "AND day LIKE :day || '%' " +
            "AND groups LIKE :group")
    LiveData<List<ScheduleEntity>> getFilteredSchedule(String subject, String teacher, String day, String group);

    @Query("SELECT * FROM schedule WHERE id LIKE :id")
    LiveData<ScheduleEntity> getScheduleById(String id);
}
