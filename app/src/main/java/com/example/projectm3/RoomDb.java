package com.example.projectm3;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Data.class},version = 1)
public abstract class RoomDb extends RoomDatabase{

    public abstract DaoCalender getDao();
    public static RoomDb instance;

    public static synchronized  RoomDb getInstance(Context context)
    {
        if (instance==null)
        {
            instance= Room.databaseBuilder(context,RoomDb.class,"db_CalernderData").build();
        }
        return instance;

    }


}
