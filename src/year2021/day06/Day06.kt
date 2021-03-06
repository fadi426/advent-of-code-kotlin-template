package year2021.day01.day06

import util.assertTrue
import util.read2021DayInput


fun main() {
    fun calcLanternFishDensity(input: List<Int>, days: Int): Long {
        var fishDensityList = (0..9).map { n -> input.count { n == it } }.map { it.toLong() }
        for (i in 1..days) {
            fishDensityList = mutableListOf(
                fishDensityList[1],
                fishDensityList[2],
                fishDensityList[3],
                fishDensityList[4],
                fishDensityList[5],
                fishDensityList[6],
                fishDensityList[7] + fishDensityList[0],
                fishDensityList[8],
                fishDensityList[0]
            )
        }
        return fishDensityList.sum()
    }

    val input = read2021DayInput("Day06.txt").first().split(",").map { it.toInt() }
    fun task01() = calcLanternFishDensity(input, 80)
    fun task02() = calcLanternFishDensity(input, 256)

    assertTrue(task01().toInt() == 360761)
    assertTrue(task02() == 1632779838045)
}
