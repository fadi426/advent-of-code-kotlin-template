package day01.day07

import assertTrue
import readDayInput
import kotlin.math.absoluteValue

fun main() {

    fun task01(input: List<Int>): Int {
        var cheapestOutcome = Int.MAX_VALUE
        for (i in 0..input.maxOrNull()!!) {
            var maxValue = 0
            for (j in input.indices) maxValue += (input[j] - i).absoluteValue
            if (maxValue < cheapestOutcome) cheapestOutcome = maxValue.absoluteValue
        }
        return cheapestOutcome
    }

    fun task02(input: List<Int>): Int {
        var cheapestOutcome = Int.MAX_VALUE
        for (i in 0..input.maxOrNull()!!) {
            var maxValue = 0
            for (j in input.indices) maxValue += (input[j] - i).absoluteValue * ((input[j] - i).absoluteValue + 1) / 2
            if (maxValue < cheapestOutcome) cheapestOutcome = maxValue.absoluteValue
        }
        return cheapestOutcome
    }

    val input = readDayInput("Day07").first().split(",").map { it.toInt() }
    assertTrue(task01(input) == 356992)
    assertTrue(task02(input) == 101268110)
}
