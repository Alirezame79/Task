package com.method.task.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Insert
    fun insert(task: Task)

    @Query("SELECT * fROM task WHERE id = :id")
    fun show(id: Int): Task
}