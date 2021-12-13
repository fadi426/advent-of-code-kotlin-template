package day01.day11

import java.awt.Point

class DumboOctopus(private var energy: Int, val position: Point) {
    var flashCount = 0

    fun isStartingToFlash(): Boolean {
        return energy == 10
    }

    fun isFlashing(): Boolean {
        return energy > 9
    }

    fun flashedLastStep(): Boolean {
        return energy == 0
    }

    fun increaseEnergy() {
        energy++
    }

    fun resetEnergy() {
        energy = 0
    }

    override fun toString(): String {
        return energy.toString()
    }
}
