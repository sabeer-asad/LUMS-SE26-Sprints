package com.example.lonelytwitter;

import java.util.Date;

public class Exquisite extends Mood {
    public Exquisite() {
        super();
    }

    public Exquisite(Date date) {
        super(date);
    }

    @Override
    public String moodIs() {
        return "'Tis truly a splendid afternoon for some tea and biscuits, innit";
    }
}
