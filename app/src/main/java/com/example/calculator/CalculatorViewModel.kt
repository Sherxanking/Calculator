package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> performCalculate()
            is CalculatorAction.Delete -> performDelete()
            is CalculatorAction.SelectHistory -> selectHistory(action.result)
        }
    }

    private fun selectHistory(result: String) {
        state = state.copy(
            number1 = result,
            number2 = "",
            operation = null
        )
    }

    private fun performDelete() {
        when {
            state.number2.isNotEmpty() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.isNotEmpty() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun performCalculate() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()

        if (number1 != null && number2 != null) {
            val result = when (state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> if (number2 != 0.0) number1 / number2 else null
                null -> null
            }

            if (result != null) {
                val resultString = if (result % 1.0 == 0.0) {
                    result.toInt().toString()
                } else {
                    result.toBigDecimal().toPlainString()
                }
                val operationSymbol = state.operation?.symbol ?: ""
                val historyEntry = "${state.number1} $operationSymbol ${state.number2} = $resultString"
                state = state.copy(
                    number1 = resultString.take(15),
                    number2 = "",
                    operation = null,
                    history = state.history + historyEntry
                )
            } else {
                state = state.copy(number1 = "Error")
            }
        }
    }


    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()) {
            if (state.operation != null && state.number2.isNotBlank()) {
                // Avvalgi amalni hisoblash
                val number1 = state.number1.toDoubleOrNull()
                val number2 = state.number2.toDoubleOrNull()
                val result = if (number1 != null && number2 != null) {
                    when (state.operation) {
                        is CalculatorOperation.Add -> number1 + number2
                        is CalculatorOperation.Subtract -> number1 - number2
                        is CalculatorOperation.Multiply -> number1 * number2
                        is CalculatorOperation.Divide -> if (number2 != 0.0) number1 / number2 else null
                        null -> null
                    }
                } else null
                val resultString = if (result != null) {
                    if (result % 1.0 == 0.0) result.toInt().toString() else result.toBigDecimal().toPlainString()
                } else {
                    "Error"
                }
                state = state.copy(
                    number1 = resultString.take(15),
                    number2 = "",
                    operation = operation
                )
            } else {
                state = state.copy(operation = operation)
            }
        }
    }

    private fun enterDecimal() {
        if (state.operation == null) {
            if (state.number1.isEmpty()) {
                state = state.copy(number1 = "0.")
            } else if (!state.number1.contains(".")) {
                state = state.copy(number1 = state.number1 + ".")
            }
        } else {
            if (state.number2.isEmpty()) {
                state = state.copy(number2 = "0.")
            } else if (!state.number2.contains(".")) {
                state = state.copy(number2 = state.number2 + ".")
            }
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if (state.number1.length < MAX_NUM_LENGTH) {
                state = state.copy(number1 = state.number1 + number)
            }
        } else {
            if (state.number2.length < MAX_NUM_LENGTH) {
                state = state.copy(number2 = state.number2 + number)
            }
        }
    }

    companion object {
        private const val MAX_NUM_LENGTH = 15
    }
}