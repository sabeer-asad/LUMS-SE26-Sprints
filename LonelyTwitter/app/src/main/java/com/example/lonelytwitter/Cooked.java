package com.example.lonelytwitter;

import java.util.Date;

public class Cooked extends Mood {
    public Cooked() {
        super();
    }

    public Cooked(Date date) {
        super(date);
    }

    @Override
    public String moodIs() {
        return "I'm soo cooked rn dawg";
    }
}
