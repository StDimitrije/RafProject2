package com.example.rafproject2.model;

public class ScheduleFilter {

    private String mSubject;
    private String mTeacher;
    private String mDay;
    private String mGroup;

    public ScheduleFilter(String subject, String teacher, String day, String group){
        mSubject = subject;
        mTeacher = teacher;
        mDay = day;
        mGroup = group;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmTeacher() {
        return mTeacher;
    }

    public void setmTeacher(String mTeacher) {
        this.mTeacher = mTeacher;
    }

    public String getmDay() {
        return mDay;
    }

    public void setmDay(String mDay) {
        this.mDay = mDay;
    }

    public String getmGroup() {
        return mGroup;
    }

    public void setmGroup(String mGroup) {
        this.mGroup = mGroup;
    }
}
