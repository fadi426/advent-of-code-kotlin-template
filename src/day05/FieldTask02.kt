package day01.day05

import java.awt.Point
import kotlin.math.absoluteValue

class FieldTask02(
    override val coordinates: List<Pair<Point, Point>>,
    override var hydrothermalVentsMap: MutableList<MutableList<Int>> = mutableListOf()
) : Field {

    init {
        createMap()
    }

    override fun analyseCoordinates() {
        coordinates.forEach { points ->
            fillHorizontalAndVertical(points)
            fillDiagonal(points)
        }
    }

    private fun fillDiagonal(points: Pair<Point, Point>) {
        val pointDifference = points.second.x - points.first.x to points.second.y - points.first.y
        if (pointDifference.first.absoluteValue != pointDifference.second.absoluteValue) return
        for (i in 0..pointDifference.first.absoluteValue) {
            if (pointDifference.first > 0) {
                if (pointDifference.second > 0) hydrothermalVentsMap[points.first.y + i][points.first.x + i]++
                if (pointDifference.second < 0) hydrothermalVentsMap[points.first.y - i][points.first.x + i]++
            } else {
                if (pointDifference.second > 0) hydrothermalVentsMap[points.first.y + i][points.first.x - i]++
                if (pointDifference.second < 0) hydrothermalVentsMap[points.first.y - i][points.first.x - i]++
            }
        }
    }
}
