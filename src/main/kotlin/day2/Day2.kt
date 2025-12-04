package day2

import common.util.loadLines
import kotlin.math.min

fun day2() {
    val ranges: List<LongRange> = loadLines("input/day2.txt")
        .flatMap { it.split(",").toList() }
        .map { range ->
            range.split("-").let { (start, end) -> LongRange(start.toLong(), end.toLong()) }
        }

    println("2: ${ranges.sumOf { calculateSum(it, 2, 2) }}")
    println("> 2: ${ranges.sumOf { calculateSum(it, 2, 999) }}")
}

fun calculateSum(range: LongRange, minSplits: Int, maxSplits: Int): Long {
    var sum = 0L

    range.forEach {
        run splitloop@{
            IntRange(minSplits, min(maxSplits, range.last.digits())).forEach { splits ->
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

fun Long.digits() = toString().length