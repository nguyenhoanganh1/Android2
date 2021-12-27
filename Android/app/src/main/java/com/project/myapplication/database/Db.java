package com.project.myapplication.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project.myapplication.dao.CustomerDAO;
import com.project.myapplication.entities.Customer;

@Database(entities = {Customer.class}, version = 1)
public abstract class Db extends RoomDatabase {
    private static final String DATABASE_NAME = "my_database";
    private static Db instance;

    public static synchronized Db getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), Db.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract CustomerDAO customerDAO();
}
