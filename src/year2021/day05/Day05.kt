package year2021.day01.day05

import util.assertTrue
import util.read2021DayInput
import java.awt.Point

fun main() {
    fun task01(coordinates: List<Pair<Point, Point>>): Int {
        val field = FieldTask01(coordinates)
        return field.countOverlappingLines()
    }

    fun task02(coordinates: List<Pair<Point, Point>>): Int {
        val field = FieldTask02(coordinates)
        return field.countOverlappingLines()
    }

    val coordinates = read2021DayInput("Day05").map {
        it.split(" -> ").map { it.split(",").let { Point(it[0].toInt(), it[1].toInt()) } }
            .let { Pair(it[0], it[1]) }
    }
    assertTrue(task01(coordinates) == 4873)
    assertTrue(task02(coordinates) == 19472)
}
