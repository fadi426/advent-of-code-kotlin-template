package year2021.day01.day09

import util.assertTrue
import util.model.Counter
import util.read2021DayInput
import java.awt.Point

fun main() {

    fun task01(input: List<List<Int>>): Int {
        return findLowPoints(input).sumOf { it.first + 1 }
    }

    fun task02(input: List<List<Int>>): Int {
        val lowPoints = findLowPoints(input)
        val basins = MutableList(input.size) { MutableList(input[0].size) { false } }
        val basinCounts = MutableList(lowPoints.count()) { Counter() }

        fun markBasins(point: Point, previous: Int, counter: Counter) {
            input.getOrNull(point.y)?.getOrNull(point.x)?.let { current ->

                if (current > previous && current != 9 && !basins[point.y][point.x]) {
                    basins[point.y][point.x] = true
                    counter.i++
                    markBasins(Point(point.x + 1, point.y), current, counter)
                    markBasins(Point(point.x - 1, point.y), current, counter)
                    markBasins(Point(point.x, point.y + 1), current, counter)
                    markBasins(Point(point.x, point.y - 1), current, counter)
                }
            }
        }

        lowPoints.forEachIndexed { index, lowPoint ->
            markBasins(lowPoint.second, lowPoint.first - 1, basinCounts[index])
        }
        return basinCounts.map { it.i }.sorted().takeLast(3).reduce { acc, basinSize -> acc * basinSize }
    }

    val input = read2021DayInput("Day09").map { it.map { "$it".toInt() } }

    assertTrue(task01(input) == 514)
    assertTrue(task02(input) == 1103130)
}


fun findLowPoints(list: List<List<Int>>): MutableList<Pair<Int, Point>> {
    val lowPoints = mutableListOf<Pair<Int, Point>>()
    for (y in list.indices) {
        for (x in list[y].indices) {
            val point = Point(x, y)
            val result = listOf(
                if (point.y + 1 > list.size - 1) null else list[point.y][point.x] < list[point.y + 1][point.x], // smaller than down
                if (point.y - 1 < 0) null else list[point.y][point.x] < list[point.y - 1][point.x], // smaller than up
                if (point.x + 1 > list[point.y].size - 1) null else list[point.y][point.x] < list[point.y][point.x + 1], // smaller than right
                if (point.x - 1 < 0) null else list[point.y][point.x] < list[point.y][point.x - 1] // smaller than left
            )
            if (!result.any { it == false }) lowPoints.add(Pair(list[y][x], Point(x, y)))
        }
    }
    return lowPoints
}
