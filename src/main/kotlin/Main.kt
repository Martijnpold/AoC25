import day3.day3
import kotlin.time.measureTime

fun main() {
    val timeSpent = measureTime {
        day3()
    }
    println("time spent: $timeSpent")
}