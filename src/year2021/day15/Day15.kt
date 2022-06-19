package year2021.day15

import util.read2021DayInput

fun main() {

    fun task1(input: List<String>): Int {
        val board = MutableList(input.size) { input[it].map { "$it".toInt() }.toMutableList() }
        return findShortestDistance(board)
    }

    fun task2(input: List<String>, expansionNumber: Int): Int {
        val board = MutableList(input.size) { input[it].map { "$it".toInt() }.toMutableList() }
        return findShortestDistance(expandBoard(board, expansionNumber))
    }

    val input = read2021DayInput("Day15")
//    assertTrue(task1(input) == 609)
//    println(task1(input))
    println(task2(input, 5))
}

fun findShortestDistance(board: MutableList<MutableList<Int>>): Int {
    val distanceFromStartBoard = board.map { it.map { Int.MAX_VALUE }.toMutableList() }.toMutableList()
    distanceFromStartBoard[0][0] = board[0][0]
    for (y in board.indices) {
        for (x in board.indices) {
            board.getOrNull(y)?.getOrNull(x + 1)?.let {
                val xValue = distanceFromStartBoard[y][x] + board[y][x + 1]
                if (xValue < distanceFromStartBoard[y][x + 1]) distanceFromStartBoard[y][x + 1] = xValue
            }
            board.getOrNull(y)?.getOrNull(x - 1)?.let {
                val xValue = distanceFromStartBoard[y][x] + board[y][x - 1]
                if (xValue < distanceFromStartBoard[y][x - 1]) distanceFromStartBoard[y][x - 1] = xValue
            }
            board.getOrNull(y + 1)?.getOrNull(x)?.let {
                val yValue = distanceFromStartBoard[y][x] + board[y + 1][x]
                if (yValue < distanceFromStartBoard[y + 1][x]) distanceFromStartBoard[y + 1][x] = yValue
            }
            board.getOrNull(y - 1)?.getOrNull(x)?.let {
                val yValue = distanceFromStartBoard[y][x] + board[y - 1][x]
                if (yValue < distanceFromStartBoard[y - 1][x]) distanceFromStartBoard[y - 1][x] = yValue
            }
        }
    }
    distanceFromStartBoard.forEach { println(it) }
    return distanceFromStartBoard.last().last() - distanceFromStartBoard[0][0]
}

fun expandBoard(board: MutableList<MutableList<Int>>, times: Int): MutableList<MutableList<Int>> {
    val fullBoard = MutableList(board.size * (times)) { mutableListOf<Int>() }
    for (i in 0 until times) {
        for (y in board.indices) {
            fullBoard[y].addAll(board[y].map { if (it + i > 9) it + i - 9 else it + i })
        }
    }
    for (i in 1 until times) {
        for (y in board.indices) {
            fullBoard[y + board.size * i].addAll(fullBoard[y].map { if (it + i > 9) it + i - 9 else it + i })
        }
    }
    return fullBoard
}
