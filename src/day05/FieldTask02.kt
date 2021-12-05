package day01.day05

import java.awt.Point
import kotlin.math.absoluteValue

class FieldTask02(
    override val coordinates: List<Pair<Point, Point>>,
    override var hydrothermalVentsMap: MutableList<MutableList<Int>> = mutableListOf()
) : Field {

    init {
        createMap()
        fillCoordinates()
    }

    override fun fillCoordinates() {
        coordinates.forEach { points ->
            fillHorizontalAndVertical(points)
            fillDiagonal(points)
        }
    }

    private fun fillDiagonal(points: Pair<Point, Point>) {
        val pointDifference = points.second.x - points.first.x to points.second.y - points.first.y
        if (pointDifference.first.absoluteValue != pointDifference.second.absoluteValue) return
        for (i in 0..pointDifference.first.absoluteValue) {
            val x = if (pointDifference.first > 0) i + points.first.x else -i + points.first.x
            val y = if (pointDifference.second > 0) i + points.first.y else -i + points.first.y
            hydrothermalVentsMap[y][x]++
        }
    }
}
