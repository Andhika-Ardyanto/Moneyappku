package com.example.moneyappku.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Uang.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance (Context context){

        if( INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class , "dbuang")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
