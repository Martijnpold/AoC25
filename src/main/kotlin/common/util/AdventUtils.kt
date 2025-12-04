package common.util

fun loadLines(file: String) =
    object {}.javaClass.getResource("/$file")!!
        .readText()
        .split("\r\n|\n|\r".toRegex())

fun loadGrid(file: String) =
    loadLines(file).map { it.toCharArray().toList() }.asGrid()

fun loadIntGrid(file: String) =
    loadLines(file).map { it.toCharArray().toList().map { "$it".toInt() } }.asGrid()
