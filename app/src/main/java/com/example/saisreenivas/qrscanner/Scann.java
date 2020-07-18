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

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.saisreenivas.qrscanner.R.id.history;

public class Scann extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView mScannerView;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scann);

        dbHandler = new DBHandler(this);

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view<br />
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);// Register ourselves as a handler for scan results.<br />
        mScannerView.startCamera();// Start camera<br /
    }

    public void handleResult(final Result rawResult) {

        // Do something with the result here
        Log.e("handler", rawResult.getText());//prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        // show the scanner result into dialog box.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());
        builder.setCancelable(true);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                QRData data = new QRData(rawResult.getBarcodeFormat().toString(), rawResult.getText());
                dbHandler.addData(data);
                mScannerView.resumeCameraPreview(Scann.this);

            }
        });
        final AlertDialog alert1 = builder.create();
        alert1.show();
        // If you would like to resume scanning after 5 seconds, call this method below:<br />
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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == history){
            Intent intent = new Intent(Scann.this, History.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}
