package com.example.projectm3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class OpenDialog extends Dialog {
    TextView eventname,eventdes,date;
    String nametxt,destext,datetxt;

    public OpenDialog(@NonNull Context context,String name,String des,String date) {
        super(context);
        this.nametxt=name;
        this.destext=des;
        this.datetxt=date;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_open_dialog);
        eventname=findViewById(R.id.openeditemtitle);
        eventdes=findViewById(R.id.openitemdes);
        date=findViewById(R.id.openitemdate);

        eventdes.setText(destext);
        eventname.setText(nametxt);
        date.setText(datetxt);

    }
}