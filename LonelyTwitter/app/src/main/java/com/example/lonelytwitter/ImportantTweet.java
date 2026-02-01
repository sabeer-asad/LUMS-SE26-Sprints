package com.example.lonelytwitter;

import java.util.Date;

public class ImportantTweet extends Tweet {
    public ImportantTweet(String message, Mood mood) {
        super(message, mood);
    }

    public ImportantTweet(String message, Date date, Mood mood) {
        super(message, date, mood);
    }

    @Override
    public Boolean isImportant() {
        return true;
    }
}
