package com.example.taskapp.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentOnBoardPageBinding
import com.example.taskapp.utils.Preferences

@Suppress("DEPRECATION")
class OnBoardPageFragment(
    private var onNextClick: () -> Unit,
    private var onSkipClick: () -> Unit
) : Fragment() {

    private lateinit var binding: FragmentOnBoardPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPageBinding.inflate(inflater , container , false)

        initViews()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
            Preferences(requireContext()).setBoardingShowed(true)
        }

        binding.btnNext.setOnClickListener {
            onNextClick.invoke()
        }

        binding.btnSkip.setOnClickListener {
            onSkipClick.invoke()
        }
    }

    private fun initViews() {
        val  data = arguments?.getSerializable("onBoard") as BoardModel
        binding.imgBoard.setImageResource(data.img)
        binding.tvTitle.text = data.title
        binding.tvDesc.text = data.description

        binding.btnSkip.isVisible = data.isLast == false //true true false
        binding.btnNext.isVisible = data.isLast == false

        binding.btnStart.isVisible = data.isLast == true // false false true
    }
}
