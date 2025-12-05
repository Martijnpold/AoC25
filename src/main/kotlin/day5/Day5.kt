package day5

import common.util.loadLines
import common.util.splitBy
import kotlin.math.min

fun day5() {
    val (ranges, tests) = loadLines("input/day5.txt").splitBy("").let { (rawRanges, tests) ->
        rawRanges.map { it.split("-") }.map { LongRange(it[0].toLong(), it[1].toLong()) } to
                tests.map { it.toLong() }
    }

    //Sort ranges and remove any ranges fully contained in another
    val sortedRanges = ranges.sortedBy { it.first }
        .distinct()
        .filter { inner -> ranges.none { outer -> inner != outer && outer.contains(inner) } }

    //Trim overlapping sections of ranges
    val noOverlap = sortedRanges.mapIndexed { i, range ->
        if (i >= sortedRanges.size - 1) return@mapIndexed range
        val next = sortedRanges[i + 1]
        LongRange(range.first, min(range.last, next.first - 1))
    }

    println("Fresh: ${tests.count { test -> noOverlap.any { it.contains(test) } }}")
    println("Potential Fresh: ${noOverlap.sumOf { it.last - it.first + 1 }}")
}

private fun LongRange.contains(other: LongRange): Boolean = contains(other.first) && contains(other.last)
