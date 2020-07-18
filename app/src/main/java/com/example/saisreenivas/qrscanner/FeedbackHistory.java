package com.example.saisreenivas.qrscanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FeedbackHistory extends AppCompatActivity {
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_history);

        dbHandler = new DBHandler(this);
        printdatabase();
    }

    public void printdatabase(){
        List<Feed> totalHistoryFeed = dbHandler.databaseToArrayFeed();
        ArrayList<String> YOUR_LIST = new ArrayList<>();

        for(Feed th: totalHistoryFeed){
            String log = "Id: " + th.get_id() + ", CodeFormat: " + th.get_date() + ", Data: " + th.get_firstName()
                    + " LastName: " + th.get_lastName() + " stars " + th.get_stars();
            YOUR_LIST.add(log);
        }

        ArrayAdapter<String> adapter = new CustomAdapter(this, YOUR_LIST);

        ListView listView = (ListView) findViewById(R.id.activity_feedhistory);
        listView.setAdapter(adapter);
    }
}
