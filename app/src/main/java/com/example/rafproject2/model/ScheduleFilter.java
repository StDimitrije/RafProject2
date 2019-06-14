package com.example.rafproject2.model;

public class ScheduleFilter {

    private String mSubject;
    private String mTeacher;
    private String mDay;
    private String mGroup;

    public ScheduleFilter(String subject,String teacher, String day, String group){
        mSubject = subject;
        mTeacher = teacher;
        mDay = day;
        mGroup = group;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) { mSubject = subject;
    }

    public String getTeacher() {
        return mTeacher;
    }

    public void setTeacher(String teacher) {
        mTeacher = teacher;
    }

    public String getDay() {
        return mDay;
    }

    public void setDay(String day) {
        mDay = day;
    }

    public String getGroup() {
        return mGroup;
    }

    public void setGroup(String group) {
        mGroup = group;
    }
}
