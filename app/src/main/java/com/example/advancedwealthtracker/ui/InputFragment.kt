package com.example.advancedwealthtracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.advancedwealthtracker.R
import com.example.advancedwealthtracker.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding: FragmentInputBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ggLiBtnSave.setOnClickListener {
            val incomeText = binding.ggLiEtIncome.text?.toString()?.trim().orEmpty()
            val expensesText = binding.ggLiEtExpenses.text?.toString()?.trim().orEmpty()

            if (!validateFields(incomeText, expensesText)) return@setOnClickListener

            val income = incomeText.toDoubleOrNull()
            val expenses = expensesText.toDoubleOrNull()
            if (income == null) {
                binding.ggLiEtIncome.error = getString(R.string.gg_li_error_invalid_number)
                return@setOnClickListener
            }
            if (expenses == null) {
                binding.ggLiEtExpenses.error = getString(R.string.gg_li_error_invalid_number)
                return@setOnClickListener
            }

            parentFragmentManager.setFragmentResult(
                REQUEST_KEY,
                Bundle().apply {
                    putDouble(BUNDLE_KEY_INCOME, income)
                    putDouble(BUNDLE_KEY_EXPENSES, expenses)
                }
            )

            parentFragmentManager.setFragmentResult(
                REQUEST_KEY_NAVIGATION,
                Bundle().apply { putInt(BUNDLE_KEY_TARGET_PAGE, 1) }
            )
        }
    }

    private fun validateFields(income: String, expenses: String): Boolean {
        var isValid = true

        if (income.isEmpty()) {
            binding.ggLiEtIncome.error = getString(R.string.gg_li_error_required)
            isValid = false
        } else {
            binding.ggLiEtIncome.error = null
        }

        if (expenses.isEmpty()) {
            binding.ggLiEtExpenses.error = getString(R.string.gg_li_error_required)
            isValid = false
        } else {
            binding.ggLiEtExpenses.error = null
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val REQUEST_KEY = "gg_li_request_savings_data"
        const val REQUEST_KEY_NAVIGATION = "gg_li_request_navigation"
        const val BUNDLE_KEY_INCOME = "gg_li_bundle_income"
        const val BUNDLE_KEY_EXPENSES = "gg_li_bundle_expenses"
        const val BUNDLE_KEY_TARGET_PAGE = "gg_li_bundle_target_page"
    }
}
