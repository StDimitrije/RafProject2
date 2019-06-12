package com.example.rafproject2.repository.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "schedule")
public class ScheduleEntity {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    private int mId;

    @ColumnInfo(name = "subject")
    private String mSubject;

    @ColumnInfo(name = "type")
    private String mType;

    @ColumnInfo(name = "teacher")
    private String mTeacher;

    @ColumnInfo(name = "groups")
    private String mGroups;

    @ColumnInfo(name = "day")
    private String mDay;

    @ColumnInfo(name = "time")
    private String mTime;

    @ColumnInfo(name = "classRoom")
    private String mClassRoom;


    public ScheduleEntity(@NonNull int id, String subject, String type, String classRoom, String teacher, String groups, String day, String time){
        mId = id;
        mSubject = subject;
        mType = type;
        mTeacher = teacher;
        mGroups = groups;
        mDay = day;
        mTime = time;
        mClassRoom = classRoom;
    }

    @NonNull
    public int getId() {
        return mId;
    }

    public void setmId(@NonNull int id) {
        mId = id;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getTeacher() {
        return mTeacher;
    }

    public void setTeacher(String teacher) {
        mTeacher = teacher;
    }

    public String getGroups() {
        return mGroups;
    }

    public void setGroups(String groups) {
        mGroups = groups;
    }

    public String getDay() {
        return mDay;
    }

    public void setDay(String day) {
        mDay = day;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getClassRoom() {
        return mClassRoom;
    }

    public void setClassRoom(String classRoom) {
        mClassRoom = classRoom;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "mId=" + mId +
                ", mSubject='" + mSubject + '\'' +
                ", mType='" + mType + '\'' +
                ", mTeacher='" + mTeacher + '\'' +
                ", mGroups='" + mGroups + '\'' +
                ", mDay='" + mDay + '\'' +
                ", mTime='" + mTime + '\'' +
                ", mClassRoom='" + mClassRoom + '\'' +
                '}';
    }

}
