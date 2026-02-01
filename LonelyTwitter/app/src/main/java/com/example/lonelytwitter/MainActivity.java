package com.example.lonelytwitter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        Tweet firstTweet = new Tweet("");
        Cooked imCooked = new Cooked();
        Exquisite splendid = new Exquisite();
        ImportantTweet impTweet = new ImportantTweet("drumpf", imCooked);
        NormalTweet normTweet = new NormalTweet("a", splendid);
        ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
        tweetList.add(impTweet);
        tweetList.add(normTweet);
    }
}