package day01.day12

import Counter
import assertTrue
import readDayInput

fun main() {

    fun task01(input: List<String>): Int {
        val caves = createCaves(input)
        val startCave = caves.first { it.name == "start" }
        val counter = Counter()
        navigate(startCave, mutableListOf(startCave), counter, false)
        return counter.i
    }

    fun task02(input: List<String>): Int {
        val caves = createCaves(input)
        val startCave = caves.first { it.name == "start" }
        val counter = Counter()
        navigate(startCave, mutableListOf(startCave), counter, true)
        return counter.i
    }

    val input = readDayInput("Day12")
    assertTrue(task01(input) == 5157)
    assertTrue(task02(input) == 144309)
}

fun navigate(cave: Cave, visited: MutableList<Cave>, counter: Counter, withSingleRevisit: Boolean) {
    cave.nearbyCaves.forEach { nearbyCave ->
        if (nearbyCave.nearbyCaves.isEmpty()) counter.i++
        else {
            val visitedSmallCaveTwice =
                visited.map { cave -> visited.count { it == cave && it.isSmallCave() } }.any { it > 1 }
            if (!visitedSmallCaveTwice && withSingleRevisit) {
                navigate(nearbyCave, (mutableListOf(nearbyCave) + visited).toMutableList(), counter, withSingleRevisit)
            } else {
                if (visited.contains(nearbyCave) && nearbyCave.isSmallCave()) return@forEach
                navigate(nearbyCave, (mutableListOf(nearbyCave) + visited).toMutableList(), counter, withSingleRevisit)
            }
        }
    }
}

fun createCaves(input: List<String>): List<Cave> {
    val caves = input.map { it.split("-") }.flatten().distinct()
        .map { Cave(it) }
    input.forEach {
        val cavePair = it.split('-')
        if (cavePair[1] != "start")
            if (cavePair[0] != "end") {
                caves.first { it.name == cavePair[0] }.addNearbyCave(caves.first { it.name == cavePair[1] })
            }
        if (cavePair[0] != "start")
            if (cavePair[1] != "end")
                caves.first { it.name == cavePair[1] }.addNearbyCave(caves.first { it.name == cavePair[0] })
    }
    return caves
}

data class Cave(val name: String) {
    val nearbyCaves = mutableListOf<Cave>()

    fun isSmallCave(): Boolean {
        return name[0].isLowerCase() && name != "start" && name != "end"
    }

    fun addNearbyCave(cave: Cave) {
        nearbyCaves.add(cave)
    }
}
