package exercise3

fun factorial(i: Long): Long =
    when {
        i == 0L -> 1L
        i == 1L -> i
        i > 1L -> i * factorial(i - 1L)
        else -> -1L
    }

fun main() {
    println("${factorial(20)}")
}
