package day01.day11

import java.awt.Point

class Cavern(val input: List<List<Int>>) {
    private var board = MutableList(input.size) { column ->
        MutableList(input.first().toList().size) { row -> DumboOctopus(input[column][row], Point(row, column)) }
    }

    fun nextStep() {
        board.forEach { column -> column.forEach { increaseAdjacent(it) } }
        board.forEach { column ->
            column.forEach { octopus ->
                if (octopus.isFlashing()) {
                    octopus.resetEnergy()
                }
            }
        }
    }

    private fun increaseAdjacent(octopus: DumboOctopus) {
        octopus.increaseEnergy()
        if (octopus.isStartingToFlash()) {
            octopus.flashCount++
            // Horizontal
            board.getOrNull(octopus.position.y)?.getOrNull(octopus.position.x + 1)?.let { increaseAdjacent(it) }
            board.getOrNull(octopus.position.y)?.getOrNull(octopus.position.x - 1)?.let { increaseAdjacent(it) }

            // Vertical
            board.getOrNull(octopus.position.y + 1)?.getOrNull(octopus.position.x)?.let { increaseAdjacent(it) }
            board.getOrNull(octopus.position.y - 1)?.getOrNull(octopus.position.x)?.let { increaseAdjacent(it) }

            // Diagonal
            board.getOrNull(octopus.position.y - 1)?.getOrNull(octopus.position.x + 1)?.let { increaseAdjacent(it) }
            board.getOrNull(octopus.position.y - 1)?.getOrNull(octopus.position.x - 1)?.let { increaseAdjacent(it) }
            board.getOrNull(octopus.position.y + 1)?.getOrNull(octopus.position.x + 1)?.let { increaseAdjacent(it) }
            board.getOrNull(octopus.position.y + 1)?.getOrNull(octopus.position.x - 1)?.let { increaseAdjacent(it) }
        }
    }

    fun getOctopuses(): List<DumboOctopus> {
        return board.flatten()
    }
}
