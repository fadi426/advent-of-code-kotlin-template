package day01

import java.io.File

fun List<Int>.itemsBiggerThanPrevious(): Int {
    var count = 0
    this.forEachIndexed { index, item ->
        if (index != 0 && item > this[index - 1]) count += 1
    }
    return count
}

inline fun <T> Iterable<T>.multiplyOf(selector: (T) -> Int): Int {
    val list = this.toList()
    var sum = selector(list.first())
    for (i in 1 until list.size) {
        sum *= selector(list[i])
    }
    return sum
}

fun assertTrue(bool: Boolean) {
    if (!bool)
        throw Exception("Objects don't match!")
}
 fun readDayInput(day: String): List<String> {
     return File("src/$day/$day.txt").readLines()
 }
