package com.method.task.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Task::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}