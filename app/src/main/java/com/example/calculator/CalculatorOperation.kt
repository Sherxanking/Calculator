package com.example.calculator

sealed class CalculatorOperation(val symbol: String) {
    object Add: CalculatorOperation("+")
    object Subtract: CalculatorOperation("-")
    object Multiply: CalculatorOperation("x")
    object Divide: CalculatorOperation("/")
    object Percent: CalculatorOperation("%")
    object Sqrt: CalculatorOperation("√")
    object Square: CalculatorOperation("x²")
}