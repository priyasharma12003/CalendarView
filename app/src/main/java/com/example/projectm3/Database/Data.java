package com.example.projectm3.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Calender_tbl")
public class Data {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "eventName")
    String eventName;
    @ColumnInfo(name = "eventDate")
    String date;
    @ColumnInfo(name = "eventDes")
    String description;

    public Data() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
