package com.example.calculator

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun testAddition() {
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("5", viewModel.state.number1)
    }

    @Test
    fun testSubtraction() {
        viewModel.onAction(CalculatorAction.Number(7))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
        viewModel.onAction(CalculatorAction.Number(4))
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("3", viewModel.state.number1)
    }

    @Test
    fun testMultiplication() {
        viewModel.onAction(CalculatorAction.Number(6))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("30", viewModel.state.number1)
    }

    @Test
    fun testDivision() {
        viewModel.onAction(CalculatorAction.Number(8))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("4", viewModel.state.number1)
    }

    @Test
    fun testDivisionByZero() {
        viewModel.onAction(CalculatorAction.Number(8))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
        viewModel.onAction(CalculatorAction.Number(0))
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("Error", viewModel.state.number1)
    }

    @Test
    fun testDecimalInput() {
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Decimal)
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Decimal)
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("4", viewModel.state.number1)
    }

    @Test
    fun testMaxNumberLength() {
        repeat(10) { viewModel.onAction(CalculatorAction.Number(9)) }
        assertEquals(15, viewModel.state.number1.length)
    }

    @Test
    fun testClearAction() {
        viewModel.onAction(CalculatorAction.Number(9))
        viewModel.onAction(CalculatorAction.Clear)
        assertEquals("", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
        assertNull(viewModel.state.operation)
    }

    @Test
    fun testDeleteAction() {
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Delete)
        assertEquals("1", viewModel.state.number1)
    }

    @Test
    fun testSingleNumberCalculate() {
        viewModel.onAction(CalculatorAction.Number(7))
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("7", viewModel.state.number1)
    }

    @Test
    fun testOnlyDecimalInput() {
        viewModel.onAction(CalculatorAction.Decimal)
        viewModel.onAction(CalculatorAction.Number(5))
        assertEquals("0.5", viewModel.state.number1)
    }

    @Test
    fun testChainedOperations() {
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(4))
        viewModel.onAction(CalculatorAction.Calculate)
        // 2 + 3 = 5, then 5 * 4 = 20
        assertEquals("20", viewModel.state.number1)
    }

    @Test
    fun testLargeNumbers() {
        repeat(8) { viewModel.onAction(CalculatorAction.Number(9)) } // 99999999
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
        repeat(8) { viewModel.onAction(CalculatorAction.Number(1)) } // 11111111
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("111111110", viewModel.state.number1)
    }
}