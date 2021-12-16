package day14

import Counter
import assertTrue
import readDayInput

fun main() {

    fun task1(input: List<String>) = findOptimalPolymer(input, 10)
    fun task2(input: List<String>) = findOptimalPolymer(input, 40)

    val input = readDayInput("Day14")
    assertTrue(task1(input) == "${3342}".toLong())
    assertTrue(task2(input) == "${3776553567525}".toLong())
}

fun findOptimalPolymer(input: List<String>, insertionLimit: Int): Long {
    val polymerTemplate = input.first()
    val rules = input.takeLast(input.size - 2).map { it.split(" -> ") }.map { Rule(it[0], it[1]) }
    polymerTemplate.windowed(2).forEach { rules.first { rule -> it == rule.name }.counter++ }
    val finalRules = rec(rules, Counter(), insertionLimit)

    val letters = rules.map { it.insertion }.distinct().map { letter ->
        Pair(letter,
            finalRules.sumOf { rule -> rule.name.count { "$it" == letter } * rule.counter } / 2
        )
    }.sortedBy { it.second }
    return calcWithOffset(polymerTemplate, letters.last()) - calcWithOffset(polymerTemplate, letters.first())
}

fun calcWithOffset(polymerTemplate: String, letter: Pair<String, Long>): Long {
    return if (letter.first.single() == polymerTemplate.first() ||
        letter.first.single() == polymerTemplate.last()
    ) letter.second + 1 else letter.second
}

fun rec(rules: List<Rule>, counter: Counter, limit: Int): List<Rule> {
    if (counter.i > limit - 1) return rules
    val temp = rules.map { it.copy() }.toMutableList()
    for (i in rules.indices) {
        if (rules[i].counter > 0) {
            temp[i].counter -= rules[i].counter
            temp[i].outcome.forEach { insertion ->
                temp.first { it.name == insertion }.counter += rules[i].counter
            }
        }
    }
    counter.i++
    return rec(temp, counter, limit)
}

data class Rule(
    val name: String,
    val insertion: String,
    var counter: Long = 0
) {
    val outcome = mutableListOf<String>()

    init {
        outcome.addAll(listOf(name.first() + insertion, insertion + name.last()))
    }
}
