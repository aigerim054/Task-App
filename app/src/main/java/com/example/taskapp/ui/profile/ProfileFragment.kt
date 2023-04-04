package com.example.taskapp.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.utils.Preferences

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
lateinit var prefs:Preferences
    private var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent()) { uri ->
        Log.e("ol", ": $uri")
      //  binding.imgProfile.setImageURI(uri)

        Glide
            .with(this)
            .load(uri)
            .circleCrop()
            .into(binding.imgProfile)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        prefs = Preferences(requireContext())
        binding.edit.setText(prefs.getName())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initViews()
    }
    private fun initListeners() {
        binding.imgProfile.setOnClickListener{
            mGetContent.launch("image/*")
        }


    }

    private fun initViews() {
binding.edit.addTextChangedListener {
    prefs.setName(binding.edit.text.toString())
      }
    }
}

