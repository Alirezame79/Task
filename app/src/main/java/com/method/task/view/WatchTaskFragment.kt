package com.method.task.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.method.task.databinding.WatchTaskBinding
import com.method.task.model.AppDatabase
import com.method.task.model.Task

class WatchTaskFragment: Fragment() {

    private var _binding: WatchTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WatchTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("task_id")

        val db = context?.let {
            Room.databaseBuilder(
                it,
                AppDatabase::class.java, "task"
            ).allowMainThreadQueries().build()
        }

        receiveTask(db, id)
    }

    private fun receiveTask(db: AppDatabase?, id: Int?) {
        val taskDao = db?.taskDao()
        val task = taskDao?.show(
                        id = id!!)

        Log.d("TOF", "task $task")

        displayTask(task)
    }

    private fun displayTask(task: Task?){
        binding.watchTaskName.text = task?.name
        binding.watchTaskAdditionalInfo.text = task?.additionalInfo
        binding.watchTaskDescription.text = task?.description

        when (task?.color) {
            1 -> binding.watchTaskName.setBackgroundColor(Color.parseColor("#E8D953"))
            2 -> binding.watchTaskName.setBackgroundColor(Color.parseColor("#E4B835"))
            3 -> binding.watchTaskName.setBackgroundColor(Color.parseColor("#DD9121"))
        }
    }


}