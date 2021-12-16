package day14

import Counter
import assertTrue
import readDayInput

fun main() {
    fun task1(input: List<String>): Int {
        val polymerTemplate = input.first()
        val rules = input.takeLast(input.size - 2).map { it.split(" -> ") }.map { Pair(it[0], it[1]) }
        val polymer = rec(polymerTemplate, rules, Counter(), 10)
        val polymerFrequencies =
            rules.map { it.second }.distinct().map { char -> Pair(char, polymer.count { it == char.single() }) }
                .sortedBy { it.second }

        return (polymerFrequencies.last().second - polymerFrequencies.first().second)
    }

    val input = readDayInput("Day14")

//    assertTrue(task1(input) == 3342)
    println(task1(input))
}


fun rec(polymerTemplate: String, rules: List<Pair<String, String>>, counter: Counter, limit: Int): String {
    val rulesFound = rules.map { polymerTemplate.contains(it.first) }
    if (!rulesFound.any { it } || counter.i > limit - 1) return polymerTemplate
    val changes = mutableListOf<Pair<Char, Int>>()
    rules.filterIndexed { index, _ -> rulesFound[index] }.forEach {
        indexesOfRule(polymerTemplate, it.first).forEach { index ->
            changes.add(Pair(it.second.single(), index + 1))
        }
    }
    counter.i++
    return rec(fuseStrings(polymerTemplate, changes), rules, counter, limit)
}

fun fuseStrings(baseString: String, strings: List<Pair<Char, Int>>): String {
    var s = baseString
    val sortedStrings = strings.sortedBy { it.second }
    for (i in sortedStrings.indices) {
        val head = s.take(sortedStrings[i].second + i)
        val tail = s.takeLast(s.length - i - sortedStrings[i].second)
        s = head + sortedStrings[i].first + tail
    }
    return s
}

fun indexesOfRule(string: String, subString: String): List<Int> {
    val list = mutableListOf<Int>()
    for (i in 0 until string.length - 1) {
        if ("${string[i]}${string[i + 1]}" == subString) {
            list.add(i)
        }
    }
    return list
}
