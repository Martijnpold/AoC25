package day4

import common.model.Direction
import common.model.Grid
import common.util.loadGrid

fun day4() {
    val grid = loadGrid("input/day4.txt")

    println("Once: ${remove(grid, false)}")
    println("Repeat: ${remove(grid, true)}")
}

fun remove(grid: Grid<Char>, repeat: Boolean): Int {
    val toRemove = grid.findAllMatching { point, char ->
        if (char != '@') return@findAllMatching false
        val neighbors = Direction.entries.count { grid.at(point + it.offset) == '@' }
        neighbors < 4
    }

    return if (repeat && toRemove.isNotEmpty()) {
        toRemove.forEach { grid.set(it, 'x') }
        toRemove.size + remove(grid, repeat)
    } else toRemove.size
}