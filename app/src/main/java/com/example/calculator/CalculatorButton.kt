package com.example.calculator

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.ButtonBlue
import com.example.calculator.ui.theme.ButtonDark
import com.example.calculator.ui.theme.ButtonLight
import com.example.calculator.ui.theme.ButtonAccent
import com.example.calculator.ui.theme.Orange

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        symbol in listOf("+", "-", "x", "/", "%", "√", "x²") -> Orange
        symbol == "AC" || symbol == "Del" -> ButtonBlue
        symbol == "=" -> ButtonAccent
        else -> ButtonDark
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() }
            .then(modifier)
    ) {
        Text(
            text = symbol,
            fontSize = 36.sp,
            color = if (backgroundColor == ButtonLight) Color.Black else Color.White
        )
    }
}
