package day02

import day01.assertTrue
import day01.day02.SubmarineTask01
import day01.day02.SubmarineTask02
import java.io.File

fun main() {
    fun task01(commands: List<String>): Int {
        val submarine = SubmarineTask01(0,0,0)
        submarine.move(commands)
        return submarine.result()
    }

    fun task02(commands: List<String>): Int {
        val submarine = SubmarineTask02(0,0,0)
        submarine.move(commands)
        return submarine.result()
    }

    val input = File("src/day02/Day02.txt").readLines()
    assertTrue(task01(input) == 1924923)
    assertTrue(task02(input) == 1982495697)
}