package com.example.taskapp.ui.home.new_task

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.TaskItemBinding
import com.example.taskapp.ui.home.TaskModel

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>()  {

    private var taskList = arrayListOf<TaskModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(list: List<TaskModel>) {
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
       holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class TaskViewHolder(private val binding: TaskItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.itemTitle.text = taskModel.title
            binding.itemDesc.text = taskModel.desc
        }
    }
}