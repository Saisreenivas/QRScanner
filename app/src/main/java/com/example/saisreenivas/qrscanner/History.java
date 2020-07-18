package com.example.saisreenivas.qrscanner;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        dbHandler = new DBHandler(this);
        printdatabase();



    }

    //print database in a array
    public void printdatabase(){
        List<QRData> totalHistory = dbHandler.databaseToArray();
        ArrayList<String> YOUR_LIST = new ArrayList<>();

        for(QRData th: totalHistory){
            String log = "Id: " + th.get_id() + ", CodeFormat: " + th.get_codeformat() + ", Data: " + th.get_data();
            YOUR_LIST.add(log);
        }

        ArrayAdapter<String> adapter = new CustomAdapter(this, YOUR_LIST);

        ListView listView = (ListView) findViewById(R.id.activity_history);
        listView.setAdapter(adapter);
    }

}
