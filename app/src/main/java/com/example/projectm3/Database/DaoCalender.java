package com.example.projectm3.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoCalender {

    @Insert
    void insertData(Data data);

    @Query("select * from Calender_tbl")
    LiveData<List<Data>> getData();

    @Delete
    void deleteItem(Data calenderData);

}
