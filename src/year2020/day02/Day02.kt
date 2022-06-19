package year2020.day02

import util.assertTrue
import util.read2020DayInput

fun main() {
    val input = read2020DayInput("Day02")
    assertTrue(task01(input) == 460)
    assertTrue(task02(input) == 251)
}


private data class Policy(val min: Int, val max: Int, val letter: Char) {
    fun oldIsValid(value: String): Boolean = value.filter { it == letter }.length in min..max
    fun newIsValid(value: String): Boolean = listOf(value[min - 1], value[max - 1]).count { it == letter } == 1
}

private data class Password(val policy: Policy, val value: String) {
    fun oldIsValid() = policy.oldIsValid(value)
    fun newIsValid() = policy.newIsValid(value)
}


private fun convertInputToPasswords(input: List<String>) = input.map { it.split(" ") }
    .map {
        Password(
            Policy(
                it[0].substringBefore("-").toInt(),
                it[0].substringAfter("-").toInt(),
                it[1].first()
            ),
            it[2]
        )
    }

private fun task01(input: List<String>) = convertInputToPasswords(input).count { it.oldIsValid() }

private fun task02(input: List<String>) = convertInputToPasswords(input).count { it.newIsValid() }

