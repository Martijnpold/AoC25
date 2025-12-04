import kotlin.time.measureTime

fun main() {
    val timeSpent = measureTime {
        day4.day4()
    }
    println("time spent: $timeSpent")
}