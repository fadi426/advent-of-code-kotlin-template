package year2021.day02

import util.assertTrue
import util.read2021DayInput
import year2021.day01.day02.SubmarineTask01
import year2021.day01.day02.SubmarineTask02

fun main() {
    fun task01(commands: List<String>): Int {
        val submarine = SubmarineTask01(0, 0, 0)
        submarine.move(commands)
        return submarine.result()
    }

    fun task02(commands: List<String>): Int {
        val submarine = SubmarineTask02(0, 0, 0)
        submarine.move(commands)
        return submarine.result()
    }

    val input = read2021DayInput("Day02")
    assertTrue(task01(input) == 1924923)
    assertTrue(task02(input) == 1982495697)
}
