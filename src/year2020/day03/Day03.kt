package year2020.day03

import java.awt.Dimension
import java.awt.Point
import java.math.BigDecimal
import util.assertTrue
import util.read2020DayInput

fun main() {
    val input = read2020DayInput("Day03")
    assertTrue(task01(input) == 242)
    assertTrue(task02(input) == BigDecimal.valueOf(2265549792))
}

private fun task02(input: List<String>): BigDecimal {
    val journeys = listOf(
        createJourney(input, Dimension(1,1)),
        createJourney(input, Dimension(3,1)),
        createJourney(input, Dimension(5,1)),
        createJourney(input, Dimension(7,1)),
        createJourney(input, Dimension(1,2))
    )
    journeys.forEach { it.start() }
    return journeys.map { it.treeCount.toBigDecimal() }
        .reduce { acc, count -> acc * count}
}


private fun task01(input: List<String>): Int {
    val journey = createJourney(input, Dimension(3,1))
    journey.start()
    return journey.treeCount
}

private fun createJourney(input: List<String>, slopeDimension: Dimension): Journey {
    val journey = Journey(
        Dimension(input[0].length, input.size),
        slopeDimension
    )
    input.forEachIndexed { y, column ->
        column.forEachIndexed { x, geology ->
            if (geology == '#') journey.plantTree(x, y)
        }
    }
    return journey
}

private data class Journey(val startingAreaDimension: Dimension, val slopeDimension: Dimension) {
    val area = MutableList(startingAreaDimension.height) { MutableList(startingAreaDimension.width) { "." } }
    val startingArea = MutableList(startingAreaDimension.height) { MutableList(startingAreaDimension.width) { "." } }
    val position = Point(0, 0)
    var treeCount = 0

    private fun treeFound() = area[position.y][position.x] == "#"

    private fun increaseAreaWidth() {
        area.forEachIndexed { i, r ->
            r.addAll(startingArea[i])
        }
    }

    private fun goalReached() = position.y >= startingAreaDimension.height

    fun plantTree(x: Int, y: Int) {
        area[y][x] = "#"
        startingArea[y][x] = "#"
    }

    fun move(xMove: Int, yMove: Int) {
        val newX = position.x + xMove
        if (newX >= area[0].size) {
            increaseAreaWidth()
        }
        position.x += xMove
        position.y += yMove
    }

    fun start() {
        while (!goalReached()) {
            if (treeFound()) {
                treeCount += 1
            }
            move(slopeDimension.width, slopeDimension.height)
        }
    }

}
