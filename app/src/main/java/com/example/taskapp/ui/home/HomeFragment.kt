package com.example.taskapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.ui.home.new_task.TaskAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter(this::onLongClickListener, this::onUpdateClickListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
    }

    private fun initListeners() {
        binding.fabHome.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment)
        }
    }

    private fun initViews() {
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = taskAdapter

        

//        setFragmentResultListener("new_task") { key, bundle ->
//            Log.e("ololo", "initViews: " + bundle.getString("title")
//                    + bundle.getString("desc")
//            )
//
//            val title = bundle.getString("title")
//            val desc = bundle.getString("desc")
//            taskAdapter.addTask(
//                TaskModel(
//                    title = binding.
//                    desc = binding.etDesc.text.toString()
//                )
//            )
//        }
//    }
        val listOfTask = App.db.TaskDao()?.getAllTask()
        taskAdapter.addTasks(listOfTask as List<TaskModel>)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort_menu) {

            val items = arrayOf("Дата", "A-z", "z-A")
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Сортировать")
            builder.setItems(items) { _, which ->
                when (which) {
                    0 -> {
                        taskAdapter.addTasks(
                            App.db.TaskDao()?.getAllTaskByDate() as List<TaskModel>
                        )
                    }
                    1 -> {
                        taskAdapter.addTasks(
                            App.db.TaskDao()?.getAllTaskByAlphabetAz() as List<TaskModel>
                        )

                    }
                    2 -> {
                        taskAdapter.addTasks(
                            App.db.TaskDao()?.getAllTaskByAlphabetZa() as List<TaskModel>
                        )
                    }
                }
            }
            builder.show()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun onLongClickListener(pos: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Удаление")
        builder.setMessage("Вы точно хотите удалить ?")

        builder.setPositiveButton("Да") { _, _ ->
            App.db.TaskDao()?.delete(taskAdapter.getTask(pos))
        }
        builder.setNegativeButton("Нет") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
    private fun onUpdateClickListener(taskMode: TaskModel) {
        findNavController().navigate(R.id.newTaskFragment, bundleOf("update" to taskMode))
    }
}
