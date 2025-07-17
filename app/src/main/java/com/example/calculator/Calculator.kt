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
    isDarkTheme: Boolean = true,
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Clear History",
                        color = Color(0xFFFF9800),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .clickable { onAction(CalculatorAction.ClearHistory) }
                            .padding(4.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    val lastHistory = state.history.takeLast(5)
                    lastHistory.forEach { entry ->
                        val result = entry.substringAfterLast("=").trim()
                        // Format numbers in history entry
                        val formattedEntry = entry
                            .replace(Regex("\\d+(?:\\.\\d+)?")) { matchResult ->
                                formatNumber(matchResult.value)
                            }
                        Text(
                            text = formattedEntry,
                            fontSize = 16.sp,
                            color = if (isDarkTheme) Color.LightGray else Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
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
                color = if (isDarkTheme) Color.White else Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Visible,
                softWrap = true
            )


            val buttonModifier = Modifier
                .aspectRatio(1f)
                .weight(1f)

            // 1-qator: AC, ±, %, /
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    modifier = buttonModifier,
                    onClick = { onAction(CalculatorAction.Clear) }
                )
                CalculatorButton(
                    symbol = "±",
                    modifier = buttonModifier,
                    onClick = { onAction(CalculatorAction.PlusMinus) }
                )
                CalculatorButton(
                    symbol = "%",
                    modifier = buttonModifier,
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Percent)) }
                )
                CalculatorButton(
                    symbol = "/",
                    modifier = buttonModifier,
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Divide)) }
                )
            }
            // 2-qator: 7,8,9,x
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                listOf("7", "8", "9").forEach { num ->
                    CalculatorButton(
                        symbol = num,
                        modifier = buttonModifier,
                        onClick = { onAction(CalculatorAction.Number(num.toInt())) }
                    )
                }
                CalculatorButton(
                    symbol = "x",
                    modifier = buttonModifier,
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Multiply)) }
                )
            }
            // 3-qator: 4,5,6,-
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                listOf("4", "5", "6").forEach { num ->
                    CalculatorButton(
                        symbol = num,
                        modifier = buttonModifier,
                        onClick = { onAction(CalculatorAction.Number(num.toInt())) }
                    )
                }
                CalculatorButton(
                    symbol = "-",
                    modifier = buttonModifier,
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Subtract)) }
                )
            }
            // 4-qator: 1,2,3,+
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                listOf("1", "2", "3").forEach { num ->
                    CalculatorButton(
                        symbol = num,
                        modifier = buttonModifier,
                        onClick = { onAction(CalculatorAction.Number(num.toInt())) }
                    )
                }
                CalculatorButton(
                    symbol = "+",
                    modifier = buttonModifier,
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Add)) }
                )
            }
            // 5-qator: C, 0(ikki ustun), ., =
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "C",
                    modifier = buttonModifier,
                    onClick = { onAction(CalculatorAction.Copy) }
                )
                CalculatorButton(
                    symbol = "0",
                    modifier = buttonModifier.weight(2f),
                    onClick = { onAction(CalculatorAction.Number(0)) }
                )
                CalculatorButton(
                    symbol = ".",
                    modifier = buttonModifier,
                    onClick = { onAction(CalculatorAction.Decimal) }
                )
                CalculatorButton(
                    symbol = "=",
                    modifier = buttonModifier,
                    onClick = { onAction(CalculatorAction.Calculate) }
                )
            }

            Spacer(Modifier.size(16.dp))
        }
    }
}


