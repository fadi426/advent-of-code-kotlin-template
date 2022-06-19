package util

import java.io.File

fun read2021DayInput(day: String) = readYearDayInput("2021", day)

fun read2020DayInput(day: String) = readYearDayInput("2020", day)

fun readYearDayInput(year: String, day: String) = File("src/year$year/$day/$day.txt").readLines()
