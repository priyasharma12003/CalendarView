package com.example.projectm3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ViewModelClass extends ViewModel {
    DaoCalender database;

    public ViewModelClass() {
        database=RoomDb.instance.getDao();
    }

    public LiveData<List<Data>> getData()
    {
        return database.getData();
    }

}
