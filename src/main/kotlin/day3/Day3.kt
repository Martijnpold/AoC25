package day3

import common.loadLines

fun day3() {
    val banks = loadLines("input/day3.txt").map { it.map { it.digitToInt() } }

    var sumTwo = 0L
    var sumTwelve = 0L

    banks.forEach { bank ->
        val valueTwo = calculateBank(bank, 0, 2)
        sumTwo += valueTwo
        val valueTwelve = calculateBank(bank, 0, 12)
        sumTwelve += valueTwelve
        println(valueTwelve)
    }

    println(sumTwo)
    println(sumTwelve)
}

fun calculateBank(bank: List<Int>, start: Int, digits: Int): Long {
    val subBank = bank.subList(start, bank.size - (digits - 1))
    val biggest = subBank.sortedDescending()[0]
    if (digits == 1) return biggest.toLong()

    val biggestIndex = subBank.indexOf(biggest)
    val next = calculateBank(bank, start + biggestIndex + 1, digits - 1)
    return "$biggest$next".toLong()
}