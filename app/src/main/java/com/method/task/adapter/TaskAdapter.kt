package com.method.task.adapter

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.method.task.R
import com.method.task.model.Task
import kotlin.coroutines.coroutineContext

class TaskAdapter(private val dataSet: List<Task>):
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val name: TextView
        val additionalInfo: TextView
        val layout: LinearLayout
        init {
            name = view.findViewById(R.id.task_name)
            additionalInfo = view.findViewById(R.id.task_additional_info)
            layout = view.findViewById(R.id.task_layout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_structure_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = dataSet[position].name
        holder.additionalInfo.text = dataSet[position].additionalInfo
        when (dataSet[position].color) {
            1 -> holder.layout.setBackgroundColor(Color.parseColor("#E8D953"))
            2 -> holder.layout.setBackgroundColor(Color.parseColor("#E4B835"))
            3 -> holder.layout.setBackgroundColor(Color.parseColor("#DD9121"))
        }

        holder.layout.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("task_id", dataSet[position].id!!)

            Navigation
                .findNavController(it)
                .navigate(R.id.action_homeFragment_to_watchTaskFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}