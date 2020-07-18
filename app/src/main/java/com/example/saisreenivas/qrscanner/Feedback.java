package com.example.saisreenivas.qrscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class Feedback extends AppCompatActivity {
    Button submit;
    TextView date;
    EditText firstName;
    EditText lastName;
    RatingBar stars;
    TextView textstar;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        dbHandler = new DBHandler(this);
        addListenerOnRatingBar();
        addListenerOnButton();

        submit = (Button) findViewById(R.id.submitFeedback);
        date = (TextView) findViewById(R.id.dateFeedback);
        firstName = (EditText) findViewById(R.id.firstFeedback);
        lastName = (EditText) findViewById(R.id.lastFeedback);
        stars = (RatingBar) findViewById(R.id.starFeedback);
        textstar = (TextView) findViewById(R.id.textstar);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feed data = new Feed(date.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), textstar.getText().toString());
                Toast.makeText(Feedback.this, "Feedback submitted", Toast.LENGTH_LONG).show();
                dbHandler.addFeed(data);
            }
            });
    }

    public void addListenerOnRatingBar() {
        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        stars = (RatingBar) findViewById(R.id.starFeedback);
        textstar = (TextView) findViewById(R.id.textstar);
        stars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                textstar.setText(String.valueOf(rating));

            }
        });
    }

    public void addListenerOnButton() {

        stars = (RatingBar) findViewById(R.id.starFeedback);
        submit = (Button) findViewById(R.id.submitFeedback);

        //if click on me, then display the current rating value.
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(Feedback.this,
                        String.valueOf(stars.getRating()),
                        Toast.LENGTH_SHORT).show();

            }

        });

    }
}
