package com.example.lonelytwitter;

import androidx.annotation.NonNull;

import java.util.Date;

public abstract class Tweet implements Tweetable {
    private Date date;
    private String message;
    private Mood mood;

    public Tweet(String message, Mood mood) {
        this.message = message;
        this.date = new Date();
        this.mood = mood;
    }

    public Tweet(String message, Date date, Mood mood) {
        this.message = message;
        this.date = date;
        this.mood = mood;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.date.toString() + " | " + this.message;
    }

    public abstract Boolean isImportant();
}
