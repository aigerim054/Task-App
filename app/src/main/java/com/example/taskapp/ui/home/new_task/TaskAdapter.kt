package com.example.taskapp.ui.home.new_task

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.TaskItemBinding
import com.example.taskapp.ui.home.TaskModel

class TaskAdapter(
    private var onLongClick: (Int) -> Unit,
    private var onUpdateClick: (TaskModel) -> Unit,
) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var arrayTask = arrayListOf<TaskModel>()

//    fun addTask(taskMode: List<TaskMode>) {
//        arrayTask.add(taskMode)
//        Log.e("ololo", "addTask" + taskMode.title)
//        notifyDataSetChanged()
//    }

    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(list: List<TaskModel>) {
        arrayTask.clear()
        arrayTask.addAll(list)
        notifyDataSetChanged()
    }

    fun getTask(position: Int): TaskModel {
        return arrayTask[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.OnBind(
            arrayTask[position]
        )
    }

    override fun getItemCount(): Int {
        return arrayTask.size
    }
    fun deleteItemsAndNotifyAdapter(pos: Int) {
        arrayTask.removeAt(pos)
        notifyItemRemoved(pos)
    }

    inner class ViewHolder(private var binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun OnBind(taskMode: TaskModel) {
            binding.itemTitle.text = taskMode.title
            binding.itemDesc.text = taskMode.desc

            itemView.setOnLongClickListener {
                Log.e("ololo", "OnBind: $adapterPosition")
                onLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
            itemView.setOnClickListener{
                onUpdateClick(taskMode)
            }
        }
    }
}