package day01

import java.io.File

fun main() {
        val input = File("src/day01/Task01.txt").readLines()
        println("Count = ${biggerThanPreviousCount(input.map { it.toInt() })}")
}
