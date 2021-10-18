package com.method.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.method.task.R
import com.method.task.adapter.TaskAdapter
import com.method.task.databinding.HomeBinding
import com.method.task.model.AppDatabase

class HomeFragment:Fragment() {

    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = context?.let {
            Room.databaseBuilder(
                it,
                AppDatabase::class.java, "task"
            ).allowMainThreadQueries().build()
        }

        setRecyclerview(db, binding)
        addTask(binding)
    }

    private fun addTask(binding: HomeBinding) {
        binding.addTaskBtn.setOnClickListener {
            Navigation
                .findNavController(it)
                .navigate(R.id.action_homeFragment_to_addingPageFragment)
        }
    }

    private fun setRecyclerview(db: AppDatabase?, binding: HomeBinding) {
        val taskDao = db?.taskDao()
        binding.taskRecyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = taskDao?.let { TaskAdapter(it.getAll()) }
        binding.taskRecyclerview.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}