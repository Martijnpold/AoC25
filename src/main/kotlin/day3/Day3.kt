package day3

import common.loadLines

fun day3() {
    val banks = loadLines("input/day3.txt").map { it.map { it.digitToInt() } }

    println("2: ${solveBanks(banks, 2)}")
    println("12: ${solveBanks(banks, 12)}")
}

fun solveBanks(banks: List<List<Int>>, digits: Int): Long {
    return banks.sumOf { calculateBank(it, 0, digits) }
}

fun calculateBank(bank: List<Int>, start: Int, digits: Int): Long {
    val subBank = bank.subList(start, bank.size - (digits - 1))
    val biggest = subBank.max()
    if (digits == 1) return biggest.toLong()

    val biggestIndex = subBank.indexOf(biggest)
    val next = calculateBank(bank, start + biggestIndex + 1, digits - 1)
    return "$biggest$next".toLong()
}