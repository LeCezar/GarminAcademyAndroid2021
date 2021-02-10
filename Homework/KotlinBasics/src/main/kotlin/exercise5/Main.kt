package exercise5

data class HeartRateEntry(val date: Long, val value: Int)

fun populateData(vararg dataPair: Pair<Long, Int>): List<HeartRateEntry> =
    dataPair.map { HeartRateEntry(it.first, it.second) }

val data = populateData(
    1612310400L to 76,
    1612310400L to 89,
    1612310400L to 44,
    1612224000L to 47,
    1612224000L to 115,
    1612224000L to 76,
    1612224000L to 87,
    1612137600L to 90,
    1612137600L to 167
)

fun main() {

    //1
    println("Minimum heart rate : ${data.minByOrNull { it.value }?.value}")

    //2
    println("Average heart rate : ${data.sumBy { it.value } / data.size}")

    //3
    print("Heart rates above 100:")
    data.forEach {
        if (it.value > 100)
            print(" ${it.value} ")
    }.also { print("\n") }

    //4 & 5
    data.groupBy { it.date }.forEach { mapEntry ->
        println("The hearth rate values on the date ${mapEntry.key} are")
        mapEntry.value.forEach { print(" ${it.value} ") }
        println("and the maximum value recorded is: ${mapEntry.value.maxByOrNull { it.value }?.value} \n")
    }
}

