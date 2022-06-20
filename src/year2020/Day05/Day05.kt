package year2020.Day05

import util.assertTrue
import util.read2020DayInput
import kotlin.math.roundToInt

fun main() {
    val input = read2020DayInput("Day05")
    assertTrue(task01(input) == 801)
    assertTrue(task02(input) == 597)
}

private fun task02(input: List<String>): Int {
    val sortedSeats =
        input.map { calculateRow(it, Range(0, 127), 0) * 8 + calculateColumn(it, Range(0, 7), 7) }.sorted()
    sortedSeats.forEachIndexed { i, s ->
        if (sortedSeats[i + 1] - s != 1) return s + 1
    }
    return 0
}

private fun task01(input: List<String>): Int {
    return input.maxOf { calculateRow(it, Range(0, 127), 0) * 8 + calculateColumn(it, Range(0, 7), 7) }
}

private fun calculateRow(characters: String, range: Range, count: Int): Int {
    return if (count == 7) {
        val half = ((range.max.toDouble() - range.min.toDouble()) / 2) + range.min
        if (characters[count] == 'B') half.roundToInt() else half.toInt()
    } else {
        val half = ((range.max.toDouble() - range.min.toDouble()) / 2) + range.min
        if (characters[count] == 'B') calculateRow(characters, Range(half.roundToInt(), range.max), count + 1)
        else calculateRow(characters, Range(range.min, half.toInt()), count + 1)
    }
}

private fun calculateColumn(characters: String, range: Range, count: Int): Int {
    return if (count == 9) {
        val half = ((range.max.toDouble() - range.min.toDouble()) / 2) + range.min
        if (characters[count] == 'R') half.roundToInt() else half.toInt()
    } else {
        val half = ((range.max.toDouble() - range.min.toDouble()) / 2) + range.min
        if (characters[count] == 'R') calculateColumn(characters, Range(half.roundToInt(), range.max), count + 1)
        else calculateColumn(characters, Range(range.min, half.toInt()), count + 1)
    }
}

private data class Range(val min: Int, val max: Int)
