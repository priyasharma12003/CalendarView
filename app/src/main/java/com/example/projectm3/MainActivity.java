package com.example.projectm3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectm3.Database.DaoCalender;
import com.example.projectm3.Database.Data;
import com.example.projectm3.Database.RoomDb;
import com.example.projectm3.Dialog.SelectDate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    ImageButton btnedit;
    DaoCalender database;
    RecyclerAdapter adapter;
    List<Data> list;
    RecyclerView recyclerView;
    String fulldate;
    TextView showall;
    ViewModelClass viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calenderview);
        btnedit = findViewById(R.id.addeventbtn);
        showall = findViewById(R.id.showAll);
        database = RoomDb.getInstance(this).getDao();

        // getSupportActionBar().setSubtitle("Calender App");
      viewmodel=ViewModelProviders.of(this).get(ViewModelClass.class);
//        viewmodel.getData().observe(this, new Observer<List<Data>>() {
//            @Override
//            public void onChanged(List<Data> data) {
//                adapter=new RecyclerAdapter(data,MainActivity.this);
//                recyclerView.setAdapter(adapter);
//            }
//        });

        showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewmodel.getData().observe(MainActivity.this, new Observer<List<Data>>() {
                    @Override
                    public void onChanged(List<Data> calenderData) {
                        list = calenderData;
                        adapter = new RecyclerAdapter(list, MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                });

            }
        });


        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate dateDialog = new SelectDate(MainActivity.this, fulldate);
                dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dateDialog.show();
            }
        });


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        list = new ArrayList<>();


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year1, int month1, int dayOfMonth) {

                btnedit.setVisibility(View.VISIBLE);
                fulldate = String.valueOf(dayOfMonth + "-" + (month1 + 1) + "-" + year1);
                String date = "";
                list.clear();
                date = String.valueOf(dayOfMonth + "-" + (month1 + 1) + "-" + year1);
                setDateLists(date);
            }
        });
    }


    public void setDateLists(String date) {
        //viewmodel and livedata for getting live results.
        list.clear();

        viewmodel.getData().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> calenderData) {

                if (!date.equals("")) {
                    for (int i = 0; i < calenderData.size(); i++) {
                        Data data = calenderData.get(i);
                        if (date.equals(data.getDate())) {
                            list.add(data);
                            adapter = new RecyclerAdapter(list, MainActivity.this);
                            recyclerView.setAdapter(adapter);

                            break;
                        } else {
                            list.clear();
                            adapter.notifyDataSetChanged();
                        }
                    }
                } else

                    list = calenderData;
                adapter = new RecyclerAdapter(list, MainActivity.this);
                recyclerView.setAdapter(adapter);


            }
        });

    }
}































//    CalendarView mCalendarView;
//    TextView mTextView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_main);
//
//
//        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                String date=dayOfMonth+"-"+ "0" +(month+1) +"-"+year;
//                mTextView.setText(date);
//            }
//        });
//    }


