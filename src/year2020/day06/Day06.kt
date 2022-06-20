package year2020.day06

import util.assertTrue
import util.read2020DayInput

fun main() {
    val input = inputToGroupInput(read2020DayInput("Day06"))
    assertTrue(task01(input) == 6903)
    assertTrue(task02(input) == 3493)
}

private fun inputToGroupInput(input: List<String>) = input.joinToString()
    .split(", ,")
    .map { it.replace(" ", "") }

private fun task01(input: List<String>) = input
    .map { it.replace(",", "") }
    .map { it.toList().distinct() }
    .sumOf { it.size }

private fun task02(input: List<String>): Int = input
        .map { it.split(",") }
        .map { it.joinToString().split(",") }
        .map { countGroupYes(it) }
        .sumOf { it }

private fun countGroupYes(list: List<String>) = list.joinToString().toList()
    .filter { it in 'a'..'z' }
    .distinct()
    .map { u -> list.count { it.any { it == u } } == list.size }
    .count { it == true }
