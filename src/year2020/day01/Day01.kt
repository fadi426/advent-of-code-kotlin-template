package year2020.day01

import util.assertTrue
import util.read2020DayInput


fun main() {
    val input = read2020DayInput("Day01").map { it.toInt() }
    assertTrue(task01(input) == 996075)
    assertTrue(task02(input) == 51810360)
}

private fun task01(input: List<Int>): Int {
    input.forEachIndexed { i, primary ->
        val inputWithoutIndex = input.toMutableList()
        inputWithoutIndex.removeAt(i)
        inputWithoutIndex.forEach { secondary ->
            if ((primary + secondary) == 2020) return primary * secondary
        }
    }
    throw Exception("No combinations could be found")
}

private fun task02(input: List<Int>): Int {
    input.forEachIndexed { i, primary ->
        val secondaryInput = input.toMutableList()
        secondaryInput.removeAt(i)
        secondaryInput.forEachIndexed { i2, secondary ->
            val tertiaryInput = secondaryInput.toMutableList()
            tertiaryInput.removeAt(i2)
            tertiaryInput.forEach { tertiary ->
                if ((primary + secondary + tertiary) == 2020) return primary * secondary * tertiary
            }
        }
    }
    throw Exception("No combinations could be found")
}
