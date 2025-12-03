package common

fun loadLines(file: String) =
    object {}.javaClass.getResource("/$file")!!
        .readText()
        .lines()
