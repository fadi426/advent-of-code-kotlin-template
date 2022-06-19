package year2021.day01

import util.assertTrue
import util.itemsBiggerThanPrevious
import util.read2021DayInput

fun main() {
    fun task01(list : List<Int>): Int {
        return list.itemsBiggerThanPrevious()
    }
    fun task02(list : List<Int>): Int {
        return list.toList().windowed(3).map { it.sum() }.itemsBiggerThanPrevious()
    }

    val input = read2021DayInput("Day01").map { it.toInt() }
    assertTrue(task01(input) == 1226)
    assertTrue(task02(input) == 1252)
}


