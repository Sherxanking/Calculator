package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.LightGray
import com.example.calculator.ui.theme.Orange
import java.text.NumberFormat
import java.util.Locale
import androidx.compose.foundation.clickable

fun formatNumber(number: String): String {
    return try {
        if (number.isEmpty()) return ""
        val formatted = NumberFormat.getNumberInstance(Locale.US).format(number.toDouble())
        formatted
    } catch (e: Exception) {
        number
    }
}

fun calculateResult(state: CalculatorState): String{
    return try {
        val num1 = state.number1.toDoubleOrNull() ?: return ""
        val num2 = state.number2.toDoubleOrNull() ?: return ""
        val result = when (state.operation){
            CalculatorOperation.Add -> num1 + num2
            CalculatorOperation.Subtract -> num1 - num2
            CalculatorOperation.Multiply -> num1 * num2
            CalculatorOperation.Divide -> if (num2 != 0.0 ) num1 / num2 else return "Error"
            else -> return ""
        }
        formatNumber(result.toString())
    } catch (e: Exception){
        ""
    }
}

@Composable
fun Calculator(
    state: CalculatorState,
    buttonSpacing: Dp = 8.dp,
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            // Tarix (history) uchun
            if (state.history.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    val lastHistory = state.history.takeLast(5)
                    lastHistory.forEach { entry ->
                        val result = entry.substringAfterLast("=").trim()
                        Text(
                            text = entry,
                            fontSize = 16.sp,
                            color = Color.LightGray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                                .background(Color(0x22222222))
                                .clickable { onAction(CalculatorAction.SelectHistory(result)) }
                        )
                    }
                }
            }

            Text(
                text = buildString {
                    append(
                        if (state.number1.endsWith("."))
                            formatNumber(state.number1.dropLast(1)) + "."
                        else
                            formatNumber(state.number1)
                    )
                    append(state.operation?.symbol ?: "")
                    append(
                        if (state.number2.endsWith("."))
                            formatNumber(state.number2.dropLast(1)) + "."
                        else
                            formatNumber(state.number2)
                    )
                    if (state.number2.isNotEmpty()) {
                        append(" = ")
                        append(calculateResult(state))
                    }
                },
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = 40.sp,
                lineHeight = 50.sp,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Visible,
                softWrap = true
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = { onAction(CalculatorAction.Clear) }
                )
                CalculatorButton(
                    symbol = "Del",
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Delete) }
                )
                CalculatorButton(
                    symbol = "/",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Divide)) }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                listOf("7", "8", "9").forEach { num ->
                    CalculatorButton(
                        symbol = num,
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Number(num.toInt())) }
                    )
                }
                CalculatorButton(
                    symbol = "x",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Multiply)) }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                listOf("4", "5", "6").forEach { num ->
                    CalculatorButton(
                        symbol = num,
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Number(num.toInt())) }
                    )
                }
                CalculatorButton(
                    symbol = "-",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Subtract)) }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                listOf("1", "2", "3").forEach { num ->
                    CalculatorButton(
                        symbol = num,
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Number(num.toInt())) }
                    )
                }
                CalculatorButton(
                    symbol = "+",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Add)) }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = { onAction(CalculatorAction.Number(0)) }
                )
                CalculatorButton(
                    symbol = ".",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Decimal) }
                )
                CalculatorButton(
                    symbol = "=",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Calculate) }
                )
            }

            Spacer(Modifier.size(16.dp))
        }
    }
}

@Preview
@Composable
private fun Pre() {
    Calculator(
        state = CalculatorState()
    ) { }
}
