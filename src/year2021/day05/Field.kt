package year2021.day01.day05

import java.awt.Point
import kotlin.math.absoluteValue

interface Field {
    val coordinates: List<Pair<Point, Point>>
    var hydrothermalVentsMap: MutableList<MutableList<Int>>

    fun createMap() {
        hydrothermalVentsMap =
            MutableList(determineMapDimensions().first) { MutableList(determineMapDimensions().second) { 0 } }
    }

    private fun determineMapDimensions(): Pair<Int, Int> {
        val x = coordinates.map { it.let { listOf(it.first.x, it.second.x) } }.flatten().maxOrNull()!!
        val y = coordinates.map { it.let { listOf(it.first.y, it.second.y) } }.flatten().maxOrNull()!!
        return Pair(x + 1, y + 1)
    }

    fun fillCoordinates() {
        coordinates.forEach { points -> fillHorizontalAndVertical(points) }
    }

    fun countOverlappingLines(): Int {
        return hydrothermalVentsMap.sumOf { it.count { it > 1 } }
    }

    fun fillHorizontalAndVertical(points: Pair<Point, Point>) {
        if (points.first.x != points.second.x && points.first.y != points.second.y) return
        val pointDifference = Pair(points.second.x - points.first.x, points.second.y - points.first.y)
        if (pointDifference.first != 0) { // horizontal
            for (i in 0..pointDifference.first.absoluteValue) {
                val x = if (pointDifference.first > 0) i + points.first.x else -i + points.first.x
                hydrothermalVentsMap[points.first.y][x]++
            }
        }
        else { // vertical
            for (i in 0..pointDifference.second.absoluteValue) {
                val y = if (pointDifference.second > 0) i + points.first.y else -i + points.first.y
                hydrothermalVentsMap[y][points.first.x]++
            }
        }
    }
}
