package day01.day06

import day01.assertTrue
import day01.readDayInput


fun main() {
    fun calcLanternFishDensity(input: List<Int>, days: Int): Long {
        val countList = (0..9).map { n -> input.count { n == it } }.map { it.toLong() }.toMutableList()

        for (i in 1..days) {
            val firstFish: Long = countList[0]
            countList[0] = countList[1]
            countList[1] = countList[2]
            countList[2] = countList[3]
            countList[3] = countList[4]
            countList[4] = countList[5]
            countList[5] = countList[6]
            countList[6] = countList[7] + firstFish
            countList[7] = countList[8]
            countList[8] = firstFish
        }
        return countList.sum()
    }

    val input = readDayInput("Day06").first().split(",").map { it.toInt() }
    fun task01() = calcLanternFishDensity(input, 80)
    fun task02() = calcLanternFishDensity(input, 256)

    assertTrue(task01().toInt() == 360761)
    assertTrue(task02() == 1632779838045)
}
