package year2021.day01.day11

import util.assertTrue
import util.read2021DayInput

fun main() {

    fun task1(input: List<List<Int>>): Int {
        val cavern = Cavern(input)
        repeat(100) {
            cavern.nextStep()
        }
        return cavern.getOctopuses().sumOf { it.flashCount }
    }

    fun task2(input: List<List<Int>>): Int {
        val cavern = Cavern(input)
        var step = 1
        while (true) {
            cavern.nextStep()
            if (!cavern.getOctopuses().any { !it.flashedLastStep() }) return (step)
            step++
        }
    }

    val input = read2021DayInput("Day11").map { it.toList().map { "$it".toInt() } }
    assertTrue(task1(input) == 1755)
    assertTrue(task2(input) == 212)
}



