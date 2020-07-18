package com.example.saisreenivas.qrscanner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sai sreenivas on 10/30/2016.
 */

class CustomAdapter extends ArrayAdapter<String>{

    public CustomAdapter(Context context, ArrayList<String> yourlist) {
        super(context, R.layout.custom_row, yourlist);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater sreenivasInflater = LayoutInflater.from(getContext());
        View customView = sreenivasInflater.inflate(R.layout.custom_row, parent, false);

        String singleRowItem = getItem(position);
        TextView sreenivasText = (TextView) customView.findViewById(R.id.sreenivasText);
        ImageView sreenivasImage = (ImageView) customView.findViewById(R.id.sreenivasImage);

        sreenivasText.setText(singleRowItem);
        sreenivasImage.setImageResource(R.drawable.qrn);
        return customView;
    }
}
