package day1

import common.loadLines
import kotlin.math.abs

fun day1() {
    val rotations = loadLines("input/day1.txt")
        .map { line ->
            val dir = if (line[0] == 'R') 1 else -1
            line.substring(1).toInt() * dir
        }

    var dial = 50
    var endZeroes = 0
    var clickZeroes = 0

    rotations.forEach { rotation ->
        val fullRotations = abs(rotation / 100)
        val partialRotations = rotation % (100 * rotation.direction())

        clickZeroes += fullRotations
        if (passesZero(dial, partialRotations)) clickZeroes++

        dial += partialRotations
        if (dial < 0) dial += 100
        dial %= 100

        if (dial == 0) endZeroes++
    }

    println("Zeroes ended: $endZeroes")
    println("Zeroes during rotation: $clickZeroes")
}

fun passesZero(dial: Int, rotation: Int): Boolean {
    return dial != 0 && ((rotation > 0 && rotation >= (100 - dial)) || (rotation < 0 && abs(rotation) >= dial))
}

fun Int.direction() = if (this < 0) -1 else 1