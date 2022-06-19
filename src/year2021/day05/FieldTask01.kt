package year2021.day01.day05

import java.awt.Point

class FieldTask01(
    override val coordinates: List<Pair<Point, Point>>,
    override var hydrothermalVentsMap: MutableList<MutableList<Int>> = mutableListOf()
) : Field {

    init {
        createMap()
        fillCoordinates()
    }
}
