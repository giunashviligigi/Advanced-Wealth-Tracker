package com.example.advancedwealthtracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.advancedwealthtracker.R
import com.example.advancedwealthtracker.WealthManager
import com.example.advancedwealthtracker.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding!!
    private val wealthManager = WealthManager()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ggLiTvName.text = getString(R.string.gg_li_name_label)
        binding.ggLiTvAge.text = getString(R.string.gg_li_age_label)
        binding.ggLiTvKValue.text = getString(R.string.gg_li_k_label, wealthManager.calculateK())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
