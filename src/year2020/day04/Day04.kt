package year2020.day04

import util.assertTrue
import util.read2020DayInput

fun main() {
    val input = formatInput(read2020DayInput("Day04"))
    assertTrue(task01(input) == 237)
    assertTrue(task02(input) == 172)
}

private fun task01(input: List<String>) = convertStringsToPassports(input)
    .map { it.lazyIsValid() }
    .count { it }

private fun task02(input: List<String>) = convertStringsToPassports(input)
    .map { it.strictIsValid() }
    .count { it }

private fun convertStringsToPassports(input: List<String>) = input.map { s ->
    Passport(
        findPropertyInString(s, "byr"),
        findPropertyInString(s, "iyr"),
        findPropertyInString(s, "eyr"),
        findPropertyInString(s, "hgt"),
        findPropertyInString(s, "hcl"),
        findPropertyInString(s, "ecl"),
        findPropertyInString(s, "pid"),
        findPropertyInString(s, "cid")
    )
}

private fun formatInput(input: List<String>): MutableList<String> {
    val formattedInput = mutableListOf("")
    var index = 0

    input.forEach {
        if (it == "") {
            index += 1
            formattedInput.add("")
        } else formattedInput[index] += " $it"
    }
    return formattedInput
}

private fun findPropertyInString(string: String, property: String) = string
    .split(" ")
    .firstOrNull { it.contains("$property:") }
    ?.substringAfter(":")

private data class Passport(
    val byr: String? = null,
    val iyr: String? = null,
    val eyr: String? = null,
    val hgt: String? = null,
    val hcl: String? = null,
    val ecl: String? = null,
    val pid: String? = null,
    val cid: String? = null
) {
    fun lazyIsValid() = !byr.isNullOrBlank() &&
            !iyr.isNullOrBlank() &&
            !eyr.isNullOrBlank() &&
            !hgt.isNullOrBlank() &&
            !hcl.isNullOrBlank() &&
            !ecl.isNullOrBlank() &&
            !pid.isNullOrBlank()

    fun strictIsValid() = lazyIsValid() &&
            isValidYear(byr, 1920..2002) &&
            isValidYear(iyr, 2010..2020) &&
            isValidYear(eyr, 2020..2030) &&
            isValidHeight(hgt) &&
            isValidHairColor(hcl) &&
            isValidEyeColor(ecl) &&
            isValidPassportId(pid)


    private fun isValidYear(year: String?, range: IntRange): Boolean = (year?.length == 4 && year.toInt() in range)
    private fun isValidHeight(height: String?): Boolean {
        val h = height ?: ""
        val regex = "(\\d*)(\\W?)(cm|in)".toRegex()

        if (!regex.matches(h ?: "")) return false
        val matchResults = regex.find(h) ?: return false

        return when (matchResults.groupValues[3]) {
            "cm" -> matchResults.groupValues[1].toInt() in 150..193
            "in" -> matchResults.groupValues[1].toInt() in 59..76
            else -> false
        }
    }

    private fun isValidHairColor(color: String?) = "^#(\\d|[a-zA-Z]){6}\$".toRegex().matches(color ?: "")
    private fun isValidEyeColor(color: String?) =
        listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(color)

    private fun isValidPassportId(id: String?): Boolean = "^\\d{9}\$".toRegex().matches(id ?: "")
}
