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
    private String mId;

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

    @ColumnInfo(name = "classroom")
    private String mClassRoom;


    public ScheduleEntity(@NonNull String id, String subject, String type, String teacher, String groups, String day, String time, String classrom){

        mId = id;
        mSubject = subject;
        mType = type;
        mTeacher = teacher;
        mGroups = groups;
        mDay = day;
        mTime = time;
        mClassRoom = classrom;
    }

    @NonNull
    public String getmId() {
        return mId;
    }

    public void setmId(@NonNull String mId) {
        this.mId = mId;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmTeacher() {
        return mTeacher;
    }

    public void setmTeacher(String mTeacher) {
        this.mTeacher = mTeacher;
    }

    public String getmGroups() {
        return mGroups;
    }

    public void setmGroups(String mGroups) {
        this.mGroups = mGroups;
    }

    public String getmDay() {
        return mDay;
    }

    public void setmDay(String mDay) {
        this.mDay = mDay;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmClassRoom() {
        return mClassRoom;
    }

    public void setmClassRoom(String mClassRoom) {
        this.mClassRoom = mClassRoom;
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
