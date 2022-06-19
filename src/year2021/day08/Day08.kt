package year2021.day01.day08

import util.assertTrue
import util.read2021DayInput

fun main() {

    fun task01(input: List<String>): Int {
        val formattedInput = input.map { it.substringAfter(" | ") }.map { it.split(" ") }
        return listOf(2, 4, 3, 7).let { uniqueSegments ->
            formattedInput.sumOf { it.count { uniqueSegments.contains(it.length) } }
        }
    }

    fun task02(input: List<String>): Int {
        var sum = 0
        val formattedInput = input.map { it.split(" | ") }.map { it.map { it.split(" ") } }
        formattedInput.forEach {
            val segments = mutableListOf<Pair<String, Int>>()
            segments.addAll(byUniqueLength(it[0]))
            segments.addAll(byLengthSix(it[0], segments))
            segments.addAll(byLengthFive(it[0], segments))
            sum += decodeSignal(it[1], segments)
        }
        return sum
    }

    val input = read2021DayInput("Day08")
    assertTrue(task01(input) == 261)
    assertTrue(task02(input) == 987553)
}

fun decodeSignal(list: List<String>, segments: List<Pair<String, Int>>): Int {
    return list.map { segments.first { e -> it.alphabetized() == e.first.alphabetized() } }.map { it.second }
        .joinToString { it.toString() }.replace(", ", "").toInt()
}

fun byUniqueLength(list: List<String>): List<Pair<String, Int>> {
    val l = listOf(Pair(1, 2), Pair(4, 4), Pair(7, 3), Pair(8, 7))
    return list.map { Pair(it, l.find { e -> e.second == it.length }) }.filter { it.second != null }
        .map { Pair(it.first, it.second!!.first) }
}

fun byLengthFive(list: List<String>, segments: List<Pair<String, Int>>): List<Pair<String, Int>> {
    val outcome = mutableListOf<Pair<String, Int>>()
    val fiveList = list.filter { it.length == 5 }
    val three = Pair(fiveList.first { containsDigitPattern(it, segments, 7) }, 3)
    val five = Pair(fiveList.first { segments.first { e -> e.second == 6 }.first.toList().containsAll(it.toList()) }, 5)
    val two = Pair(fiveList.first { it != three.first && it != five.first }, 2)
    outcome.addAll(listOf(three, five, two))
    return outcome
}

fun byLengthSix(list: List<String>, segments: List<Pair<String, Int>>): List<Pair<String, Int>> {
    val outcome = mutableListOf<Pair<String, Int>>()
    val sixList = list.filter { it.length == 6 }
    val six = Pair(sixList.first { !containsDigitPattern(it, segments, 1) }, 6)
    val nine = Pair(sixList.first { containsDigitPattern(it, segments, 4) }, 9)
    val zero = Pair(sixList.first { it != six.first && it != nine.first }, 0)
    outcome.addAll(listOf(six, nine, zero))
    return outcome
}

fun containsDigitPattern(a: String, list: List<Pair<String, Int>>, i: Int): Boolean {
    return a.toList().containsAll(list.first { it.second == i }.first.toList())
}

fun String.alphabetized() = String(toCharArray().apply { sort() })
