package day2

import common.loadLines
import kotlin.math.min

fun day2() {
    val ranges: List<Pair<Long, Long>> = loadLines("input/day2.txt")
        .flatMap { it.split(",").toList() }
        .map { range ->
            range.split("-").let { (start, end) -> start.toLong() to end.toLong() }
        }

    var sumTwo = 0L
    var sumMoreThanTwo = 0L
    ranges.forEach { (start, end) ->
        sumTwo += calculateSum(start, end, 2, 2)
        sumMoreThanTwo += calculateSum(start, end, 2, 999)
    }
    println(sumTwo)
    println(sumMoreThanTwo)
}

fun Long.digits() = toString().length

fun calculateSum(start: Long, end: Long, minSplits: Int, maxSplits: Int): Long {
    var sum = 0L

    LongRange(start, end).forEach {
        run splitloop@{
            IntRange(minSplits, min(maxSplits, end.digits())).forEach { splits ->
                val digits = it.digits()
                if (digits % splits != 0) return@forEach
                val section = it.toString().substring(0, digits / splits)
                val compare = section.repeat(splits).toLong()
                if (it == compare) {
                    sum += compare
                    return@splitloop
                }
            }
        }
    }

    return sum
}