package day01.day05

import java.awt.Point
import kotlin.math.absoluteValue

interface Field {
    val coordinates: List<Pair<Point, Point>>
    var hydrothermalVentsMap: MutableList<MutableList<Int>>

    fun createMap() {
        for (i in 0..determineDimensions().first!!) {
            hydrothermalVentsMap.add(mutableListOf())
            for (j in 0..determineDimensions().second!!) {
                hydrothermalVentsMap[i].add(0)
            }
        }
    }

    private fun determineDimensions(): Pair<Int?, Int?> {
        val x = coordinates.map { it.let { listOf(it.first.x, it.second.x) } }.flatten().maxOrNull()
        val y = coordinates.map { it.let { listOf(it.first.y, it.second.y) } }.flatten().maxOrNull()
        return Pair(x, y)
    }

    fun analyseCoordinates() {
        coordinates.forEach { points ->
            fillHorizontalAndVertical(points)
        }
    }

    fun countOverlappingLines(): Int {
        return hydrothermalVentsMap.sumOf { it.count { it > 1 } }
    }

    fun fillHorizontalAndVertical(points: Pair<Point, Point>) {
        if (points.first.x != points.second.x && points.first.y != points.second.y) return
        val pointDifference = Pair(points.second.x - points.first.x, points.second.y - points.first.y)
        if (pointDifference.first != 0) {
            for (i in 0..pointDifference.first.absoluteValue) {
                if (pointDifference.first > 0) hydrothermalVentsMap[points.first.y][points.first.x + i]++
                else hydrothermalVentsMap[points.first.y][points.first.x - i]++
            }
        }
        if (pointDifference.second != 0) {
            for (i in 0..pointDifference.second.absoluteValue) {
                if (pointDifference.second > 0) hydrothermalVentsMap[points.first.y + i][points.first.x]++
                else hydrothermalVentsMap[points.first.y - i][points.first.x]++
            }
        }
    }
}
