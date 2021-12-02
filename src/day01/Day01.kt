package day01

import java.io.File
fun main() {
    fun task01(list : List<Int>): Int {
        return list.itemsBiggerThanPrevious()
    }
    fun task02(list : List<Int>): Int {
        return list.toList().windowed(3).map { it.sum() }.itemsBiggerThanPrevious()
    }

    val input = File("src/day01/Day01.txt").readLines().map { it.toInt() }
    assertTrue(task01(input) == 1226)
    assertTrue(task02(input) == 1252)
}


