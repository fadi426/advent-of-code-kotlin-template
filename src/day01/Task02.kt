package day01

import java.io.File

    fun main() {
        val input = File("src/day01/Task02.txt").readLines()
        val groupedList = groupListByTree(input.map { it.toInt() })
        println("Count = ${biggerThanPreviousCount(groupedList)}")
    }
