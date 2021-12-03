package day01.day03

import day01.assertTrue
import java.io.File

fun main() {

    fun task01(positions: List<String>): Int {
        var gamma = ""; var epsilon = ""
        positions[0].forEachIndexed { row, _ ->
            gamma = gamma.plus(findMostOccurringBitInRow(positions, row))
            epsilon = epsilon.plus(findLeastOccurringBitInRow(positions, row))
        }
        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
    }

    fun task02(positions: List<String>): Int {
        var oxGenRating = positions ; var co2ScrubRating = positions
        positions[0].forEachIndexed { row, _ ->
            oxGenRating = oxGenRating.filter { it[row] == findMostOccurringBitInRow(oxGenRating, row) }
            co2ScrubRating = co2ScrubRating.filter { it[row] == findLeastOccurringBitInRow(co2ScrubRating, row) }
        }
        return Integer.parseInt(oxGenRating.first(), 2) * Integer.parseInt(co2ScrubRating.first(), 2)
    }

    val input = File("src/day03/Day03.txt").readLines()
    assertTrue(task01(input) == 1458194)
    assertTrue(task02(input) == 2829354)
}

fun findMostOccurringBitInRow(bits: List<String>, row: Int): Char {
    val frequencyOneBit = bits.map { it[row] }.count { it == '1' }
    return if (frequencyOneBit >= bits.size.toDouble() / 2 && frequencyOneBit != 0) '1' else '0'
}

fun findLeastOccurringBitInRow(bits: List<String>, row: Int): Char {
    val frequencyZeroBit = bits.map { it[row] }.count { it == '0' }
    return if (bits.map { it[row] }.size == 1) bits[0][row] else if (frequencyZeroBit <= bits.size.toDouble() / 2 && frequencyZeroBit != 0) '0' else '1'
}
