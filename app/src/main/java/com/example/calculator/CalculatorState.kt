package com.example.calculator

data class CalculatorState(
    val number1: String = "0",
    val number2: String = "",
    val operation: CalculatorOperation? = null,
    val history: List<String> = emptyList()
)
