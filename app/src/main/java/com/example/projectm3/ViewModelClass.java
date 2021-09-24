package com.example.projectm3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectm3.Database.DaoCalender;
import com.example.projectm3.Database.Data;
import com.example.projectm3.Database.RoomDb;

import java.util.List;

public class ViewModelClass extends ViewModel {
    DaoCalender database;

    public ViewModelClass() {
        database= RoomDb.instance.getDao();
    }

    public LiveData<List<Data>> getData()
    {
        return database.getData();
    }

}
