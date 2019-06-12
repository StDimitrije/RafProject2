package com.example.rafproject2.model;

public class Schedule {

    private int mId;
    private String mSubject;
    private String mType;
    private String mTeacher;
    private String mGroups;
    private String mDay;
    private String mTime;
    private String mClassRoom;

    public Schedule(String subject, String type, String teacher, String groups, String day, String time, String classRoom){
        mSubject = subject;
        mType = type;
        mTeacher = teacher;
        mGroups = groups;
        mDay = day;
        mTime = time;
        mClassRoom = classRoom;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
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
}
