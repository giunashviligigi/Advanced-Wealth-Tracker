package com.example.advancedwealthtracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.advancedwealthtracker.R
import com.example.advancedwealthtracker.WealthManager
import com.example.advancedwealthtracker.databinding.FragmentAnalyticsBinding

class AnalyticsFragment : Fragment() {

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding: FragmentAnalyticsBinding
        get() = _binding!!

    private val wealthManager = WealthManager()
    private var latestIncome = 0.0
    private var latestExpenses = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener(
            InputFragment.REQUEST_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            latestIncome = bundle.getDouble(InputFragment.BUNDLE_KEY_INCOME)
            latestExpenses = bundle.getDouble(InputFragment.BUNDLE_KEY_EXPENSES)
            updateUiIfReady()
        }

        updateUiIfReady()
    }

    private fun updateUiIfReady() {
        val currentBinding = _binding ?: return
        val k = wealthManager.calculateK()
        val savings = wealthManager.calculateSavings(latestIncome, latestExpenses)

        currentBinding.ggLiTvIncomeValue.text =
            getString(R.string.gg_li_income_label, latestIncome)
        currentBinding.ggLiTvExpensesValue.text =
            getString(R.string.gg_li_expenses_label, latestExpenses)
        currentBinding.ggLiTvResult.text =
            getString(R.string.gg_li_savings_label, savings, k)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
