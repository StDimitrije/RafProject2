package com.example.rafproject2.repository.web.model;

import com.google.gson.annotations.SerializedName;

public class ScheduleApiModel {

    @SerializedName("predmet")
    private String mSubject;
    @SerializedName("tip")
    private String mType;
    @SerializedName("nastavnik")
    private String mTeacher;
    @SerializedName("grupe")
    private String mGroups;
    @SerializedName("dan")
    private String mDay;
    @SerializedName("termin")
    private String mTime;
    @SerializedName("ucionica")
    private String mClassRoom;



    public String getSubject() {
        return mSubject;
    }

    public String getType() {
        return mType;
    }

    public String getTeacher() {
        return mTeacher;
    }

    public String getGroups() {
        return mGroups;
    }

    public String getDay() {
        return mDay;
    }

    public String getTime() {
        return mTime;
    }

    public String getClassRoom() {
        return mClassRoom;
    }
}
