package util

fun List<Int>.itemsBiggerThanPrevious(): Int {
    var count = 0
    this.forEachIndexed { index, item ->
        if (index != 0 && item > this[index - 1]) count += 1
    }
    return count
}
