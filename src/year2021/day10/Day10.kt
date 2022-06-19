package year2021.day01.day10

import util.assertTrue
import util.read2021DayInput

fun main() {
    fun task1(input: List<String>, chunks: List<Pair<Char, Char>>): Int {
        val lastCharsInCorruptedLines = input.mapNotNull { isCorrupted(it, chunks) }
        return calculateCorruptedScore(lastCharsInCorruptedLines)
    }

    fun task2(input: List<String>, chunks: List<Pair<Char, Char>>): Long {
        val incompleteLines = input.filter { isCorrupted(it, chunks) == null }
        val lineCompletions = incompleteLines.map { completeLines(it, chunks) }
        return calculateIncompleteScore(lineCompletions)
    }

    val input = read2021DayInput("Day10")
    val chunks = mutableListOf(
        Pair('(', ')'),
        Pair('[', ']'),
        Pair('{', '}'),
        Pair('<', '>')
    )
    assertTrue(task1(input, chunks) == 413733)
    assertTrue(task2(input, chunks) == 3354640192)
}

fun completeLines(line: String, chunks: List<Pair<Char, Char>>): List<Char> {
    val lastChunks = mutableListOf<Char>()
    for (i in line.indices) {
        if (chunks.any { it.first == line[i] }) lastChunks.add(line[i])
        if (chunks.any { it.second == line[i] }) {
            if (chunks.first { it.second == line[i] }.first == lastChunks.last()) lastChunks.removeAt(lastChunks.size - 1)
        }
    }
    return lastChunks.map { chunk -> chunks.first { it.first == chunk }.second }
}

fun isCorrupted(line: String, chunks: List<Pair<Char, Char>>): Char? {
    val lastChunks = mutableListOf<Char>()
    for (i in line.indices) {
        if (chunks.any { it.first == line[i] }) lastChunks.add(line[i])
        if (chunks.any { it.second == line[i] }) {
            if (chunks.first { it.second == line[i] }.first == lastChunks.last()) lastChunks.removeAt(lastChunks.size - 1)
            else return line[i]
        }
    }
    return null
}

fun calculateCorruptedScore(list: List<Char>): Int {
    val pointsSystem = mutableListOf(
        Pair(')', 3),
        Pair(']', 57),
        Pair('}', 1197),
        Pair('>', 25137)
    )

    return list.sumOf { char -> pointsSystem.first { char == it.first }.second }
}

fun calculateIncompleteScore(list: List<List<Char>>): Long {
    val pointsSystem = mutableListOf(
        Pair(')', 1L),
        Pair(']', 2L),
        Pair('}', 3L),
        Pair('>', 4L)
    )

    val scoreList = list.map { chars ->
        chars.reversed().map { char ->
            pointsSystem.first { it.first == char }.second
        }.reduce { acc, scorePoints -> acc * 5L + scorePoints }
    }
    return scoreList.sortedDescending()[scoreList.size / 2]
}
