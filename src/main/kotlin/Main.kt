import kotlin.time.measureTime

fun main() {
    val timeSpent = measureTime {
        day5.day5()
    }
    println("time spent: $timeSpent")
}