package com.example.rafproject2.model;

public class Chat {
    private String mSender;
    private String mReceiver;
    private String mMessage;

    public Chat(String sender, String receiver, String message){
        mSender = sender;
        mReceiver = receiver;
        mMessage = message;
    }

    public Chat(){}


    public String getSender() {
        return mSender;
    }

    public void setSender(String sender) {
        mSender = mSender;
    }

    public String getReceiver() {
        return mReceiver;
    }

    public void setReceiver(String receiver) {
        mReceiver = mReceiver;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = mMessage;
    }
}
