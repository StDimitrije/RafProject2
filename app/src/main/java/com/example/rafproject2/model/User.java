package com.example.rafproject2.model;

public class User {

    private String mId;

    private String mIndexId;

    private String mName;

    public User(String indexId, String name) {
        mIndexId = indexId;
        mName = name;
    }

    public User(){}

    public String getId(){
        return mId;
    }

    public void setId(String id){
        mId=id;
    }

    public String getIndexId() {
        return mIndexId;
    }

    public void setIndexId(String indexId) {
        mIndexId = indexId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "mIndexId='" + mIndexId + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }
}
