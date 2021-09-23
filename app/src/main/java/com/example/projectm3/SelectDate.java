package com.example.projectm3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SelectDate extends Dialog {
    EditText eventname,eventdes;
    Button submitbtn;
    Data data;
    Context context;
    String fulldate;
    DaoCalender database;
    public SelectDate(@NonNull Context context, String fulldate) {
        super(context);
        this.context=context;
        this.fulldate=fulldate;
        database=RoomDb.instance.getDao();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_select_date);
        eventdes=findViewById(R.id.eventdes);
        eventname=findViewById(R.id.eventnameedittxt);
        submitbtn=findViewById(R.id.btnsubmit);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data=new Data();
                        data.setDate(fulldate);
                        data.setEventName(eventname.getText().toString());
                        data.setDescription(eventdes.getText().toString());
                        database.insertData(data);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Event Added!", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).start();
            }
        });

    }
}