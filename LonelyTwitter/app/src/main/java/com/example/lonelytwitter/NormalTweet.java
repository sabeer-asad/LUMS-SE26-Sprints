package com.example.lonelytwitter;

import java.util.Date;

public class NormalTweet extends Tweet {
    public NormalTweet(String message, Mood mood) {
        super(message, mood);
    }

    public NormalTweet(String message, Date date, Mood mood) {
        super(message, date, mood);
    }

    @Override
    public Boolean isImportant() {
        return false;
    }
}
