package day01

fun List<Int>.itemsBiggerThanPrevious(): Int {
    var count = 0
    this.forEachIndexed { index, item ->
        if (index != 0 && item > this[index - 1]) count += 1
    }
    return count
}

fun assertTrue(bool: Boolean) {
    if (!bool)
        throw Exception("Objects don't match!")
}