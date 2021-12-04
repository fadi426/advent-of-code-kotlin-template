package day01.day04

import day01.assertTrue
import java.io.File

fun main() {
    val input = File("src/day04/Day04.txt").readLines()
    fun task01() = calculateScores(input).first().first
    fun task02() = calculateScores(input).last().first
    assertTrue(task01() == 63424)
    assertTrue(task02() == 23541)
}

fun calculateScores(input: List<String>): MutableList<Pair<Int, Int>> {
    val list = input.map { it.replace(" ", ",").split(",").filter { it.isNotBlank() } }
    val drawnOrder = list[0]
    val bingoList = mutableListOf<MutableList<MutableList<String>>>()
    list.drop(1).toMutableList().forEach {
        if (it.isEmpty()) bingoList.add(mutableListOf()) else bingoList[bingoList.size - 1].add(it as MutableList<String>)
    }
    val scoreList = mutableListOf<Pair<Int, Int>>()
    drawnOrder.forEachIndexed { i, number ->
        val numbersDrawn = drawnOrder.take(i + 1)
        bingoList.forEachIndexed { j, board ->
            if (checkBingo(board, numbersDrawn) && !scoreList.any { it.second == j }) {
                val uncheckedNumbers = board.map { it.filter { !numbersDrawn.contains(it) } }
                scoreList.add(Pair(uncheckedNumbers.flatten().sumOf { it.toInt() } * number.toInt(), j))
            }
        }
    }
    return scoreList
}

fun checkBingo(bingoBoard: MutableList<MutableList<String>>, numbersDrawn: List<String>): Boolean {
    bingoBoard.forEachIndexed {i, column ->
        if (numbersDrawn.containsAll(column)) return true  // horizontal
        if (numbersDrawn.containsAll(column.mapIndexed { l, _ -> bingoBoard[l][i] })) return true // vertical
    }
    return false
}
