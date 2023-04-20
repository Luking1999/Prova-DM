package com.example.provadm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.provadm.data.dao.SalaDAO
import com.example.provadm.data.entity.SalaEntity

@Database(entities = [SalaEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun salaDAO(): SalaDAO

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            synchronized(this) {
                var instance: AppDataBase? = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        "app_database"
                    ).allowMainThreadQueries().build()
                }
                return instance
            }
        }
    }

}