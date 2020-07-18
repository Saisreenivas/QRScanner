package com.example.saisreenivas.qrscanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.Result;

import java.sql.Savepoint;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity {
    Button feedback;
    Button deleteHistory;
    Button qrscanner;
    Button history;
    Button feedHistory;

    ZXingScannerView mScannerView;
    public final static String EXTRA_MESSAGE = "com.example.saisreenivas.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        feedback = (Button) findViewById(R.id.feedback);
        qrscanner = (Button) findViewById(R.id.qrscanner);
        history = (Button) findViewById(R.id.history);
        deleteHistory = (Button) findViewById(R.id.deleteHistory);
        feedHistory = (Button) findViewById(R.id.feedhistory);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, History.class);
                startActivity(intent);
            }
        });

        qrscanner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Scann.class));
            }
        });

        deleteHistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Title");
                builder.setItems(new CharSequence[]
                                {"button 1", "button 2", "button 3", "button 4"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
                                        Toast.makeText(getApplicationContext(), "clicked 1", Toast.LENGTH_LONG).show();
                                        break;
                                    case 1:
                                        Toast.makeText(getApplicationContext(), "clicked 2", Toast.LENGTH_LONG).show();
                                        break;
                                    case 2:
                                        Toast.makeText(getApplicationContext(), "clicked 3", Toast.LENGTH_LONG).show();
                                        break;
                                    case 3:
                                        Toast.makeText(getApplicationContext(), "clicked 4", Toast.LENGTH_LONG).show();
                                        break;
                                }
                            }
                        });
                builder.create().show();
            }
        }
        );

        feedHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedbackHistory.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        if(mScannerView != null)
            mScannerView.stopCamera();
        mScannerView = null;// Stop camera on pause<br />

        Log.d("onPause Event", "Error...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;

            case R.id.feedback:
                startActivity(new Intent(getApplicationContext(), Feedback.class));
                break;

            case R.id.camera_preview:
                Intent intent = new Intent(getApplicationContext(), Feedback.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

}
