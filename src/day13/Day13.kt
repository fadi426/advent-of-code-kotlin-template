package day01.day13

import Counter
import assertTrue
import java.awt.Point
import readDayInput

fun main() {

    fun task1(input: List<String>): Int {
        val foldInstructions = input.filter { it.contains("fold along") }
        val board = foldRecursively(Cave(input).board, foldInstructions, Counter(), 1)
        return board.sumOf { it.count { it == "#" } }
    }

    fun task2(input: List<String>): Int {
        val foldInstructions = input.filter { it.contains("fold along") }
        val board = foldRecursively(Cave(input).board, foldInstructions, Counter(), foldInstructions.size)
        board.forEach { println(it) }
        return board.sumOf { it.count { it == "#" } }
    }

    val input = readDayInput("Day13")
    assertTrue(task1(input) == 720)
    assertTrue(task2(input) == 104)
}

class Cave(val input: List<String>) {
    private val points =
        input.filter { it.contains(",") }.map { it.split(",") }.map { Point(it[0].toInt(), it[1].toInt()) }
    val board =
        MutableList(points.maxByOrNull { it.y }!!.y + 1) {
            MutableList(points.maxByOrNull { it.x }!!.x + 1) { "." }
        }

    init {
        points.forEach {
            board[it.y][it.x] = "#"
        }
    }
}

fun combineBoards(boards: Pair<List<List<String>>, List<List<String>>>): List<List<String>> {
    val combinedList = mutableListOf<MutableList<String>>()
    combinedList.addAll(boards.first.toMutableList().map { it.toMutableList() })
    for (y in boards.second.indices) {
        for (x in boards.second[y].indices) {
            if (boards.second[y][x] == "#") combinedList[y][x] = "#"
        }
    }
    return combinedList
}

fun foldRecursively(
    board: List<List<String>>,
    foldInstructions: List<String>,
    counter: Counter,
    foldLimit: Int
): List<List<String>> {
    if (foldInstructions.isEmpty() || counter.i > foldLimit - 1)
        return board
    val newFoldInstructions = foldInstructions.takeLast(foldInstructions.size - 1)
    counter.i++
    return foldRecursively(fold(board, foldInstructions.first()), newFoldInstructions, counter, foldLimit)
}

fun fold(board: List<List<String>>, foldInstruction: String): List<List<String>> {
    val foldLine = foldInstruction.split("=")[1].toInt()
    return if (foldInstruction.contains("y")) foldY(board, foldLine)
    else foldX(board, foldLine)
}

fun foldY(board: List<List<String>>, foldLine: Int): List<List<String>> {
    return combineBoards(Pair(board.take(foldLine), board.takeLast(foldLine).reversed()))
}

fun foldX(board: List<List<String>>, foldLine: Int): List<List<String>> {
    return combineBoards(Pair(board.map { it.take(foldLine) }, board.map { it.takeLast(foldLine).reversed() }))
}

fun filterFold(input: List<String>, searchString: String): Int {
    return input.first { it.contains(searchString) }.split("=")[1].toInt()
}
