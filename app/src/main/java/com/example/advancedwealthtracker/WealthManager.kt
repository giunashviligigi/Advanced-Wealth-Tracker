package com.example.advancedwealthtracker

class WealthManager {

    private val firstName = "Gigi"
    private val surname = "Giunashvili"
    private val birthDay = 21

    fun calculateSavings(income: Double, expenses: Double): Double {
        return (income - expenses) * calculateK()
    }

    fun calculateK(): Double {
        return (firstName.length + surname.length).toDouble() / birthDay
    }

    fun isSurnameStartsWithVowel(): Boolean {
        return when (surname.firstOrNull()?.lowercaseChar()) {
            'a', 'e', 'i', 'o', 'u' -> true
            else -> false
        }
    }
}
