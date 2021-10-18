package com.method.task.view

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.utils.ViewState
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.method.task.databinding.AddingPageBinding
import com.method.task.model.AppDatabase
import com.method.task.model.IdPreferences
import com.method.task.model.Task

class AddingPageFragment: Fragment() {

    private var _binding: AddingPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddingPageBinding.inflate(inflater, container, false)
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

        addTaskBtn(db)
    }

    private fun addTaskBtn(db: AppDatabase?) {
        binding.saveBtn.setOnClickListener{
            if(binding.taskNameEdittext.text.toString().trim() == "" ||
                binding.taskDescriptionEdittext.text.toString().trim() == ""){
                Toast.makeText(context, "Fill Name and Description blank!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val idPreferences = IdPreferences(context)
            val taskDao = db?.taskDao()
            taskDao?.insert(
                Task(
                    id = idPreferences.getId(), // get from sharedPreferences
                    name = binding.taskNameEdittext.text.toString(),
                    description = binding.taskDescriptionEdittext.text.toString(),
                    additionalInfo = binding.taskAdditionalInfoEdittext.text.toString(),
                    color = necessityCheck()) // get from radio button and group
            )
            Log.d("TOF", "task ${idPreferences.getId()} ${necessityCheck()}")
            idPreferences.putId(idPreferences.getId() + 1)

            Toast.makeText(context, "Task Saved!", Toast.LENGTH_LONG).show()
            binding.saveBtn.visibility = View.GONE
        }
    }

    private fun necessityCheck(): Int{
        return when {
            binding.level1Necessity.isChecked -> 1
            binding.level3Necessity.isChecked -> 3
            else -> 2
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}