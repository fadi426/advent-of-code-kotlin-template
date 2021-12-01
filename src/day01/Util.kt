package day01

fun biggerThanPreviousCount(list: List<Int>): Int {
    var count = 0
    list.forEachIndexed { index, item ->
        if (index != 0) {
            if (item > list[index - 1]) {
                count += 1
            }
        }
    }
    return count
}

fun groupListByTree(list: List<Int>): List<Int> {
    val groupedList = mutableListOf<Int>()
    list.forEachIndexed { index, item ->
        when (index) {
            list.size - 2 -> groupedList.add(item + list[index + 1])
            list.size - 1 -> groupedList.add(item)
            else -> groupedList.add(item + list[index + 1] + list[index + 2])
        }
    }
    return groupedList
}
