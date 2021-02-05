package exercise1

import kotlin.random.Random

//first is systolic; second is diastolic
fun generateBloodPressureValues(): Pair<Int, Int> =
    Random.nextInt(90, 201) to Random.nextInt(60, 141)


fun interpretValues(bPValues: Pair<Int, Int>): BloodPressureCategory =
    when {

        //When it comes to the "or" logic we need to verify high to low
        //to avoid cases like systolic 181 diastolic 81 that would result in
        //High Blood pressure Stage 1 instead of Hypersensitive Crisis, if we did low to high,
        //because "when" verifies sequentially

        bPValues.first >= 180 || bPValues.second >= 120 -> BloodPressureCategory.HC
        bPValues.first in 140..179 || bPValues.second in 90..119 -> BloodPressureCategory.HBP2
        bPValues.first in 130..139 || bPValues.second in 80..89 -> BloodPressureCategory.HBP1
        bPValues.first in 120..129 && bPValues.second < 80 -> BloodPressureCategory.ELEVATED
        bPValues.first < 120 && bPValues.second < 80 -> BloodPressureCategory.NORMAL
        else -> BloodPressureCategory.ERR
    }

fun main() {
    val bPValuesArray = Array(20) { generateBloodPressureValues() }

    bPValuesArray.forEach { bPValues ->
        println(
            "Systolic: ${bPValues.first} | " +
                    "Diastolic: ${bPValues.second}  " +
                    "--> Category: ${interpretValues(bPValues).category}"
        )
    }
}
