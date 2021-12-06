package day01.day06

import day01.readDayInput

fun main() {

    fun task01(input: List<Int>, days: Int): Int {
        var aList = input.toMutableList()
        for (i in 0 until days) {
            val bList = mutableListOf<Int>()
            for (j in 0 until aList.size) {
                val fishCounter = aList[j]
                if (fishCounter != 0) bList.add(fishCounter -1)
                else {
                    bList.add(6)
                    bList.add(8)
                }
            }
            aList = bList
        }
        return aList.size
    }

    val input = readDayInput("Day06").first().split(",").map { it.toInt() }
    println(task01(input, 80))
}
