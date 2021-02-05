package exercise2

abstract class Card(val CardID: Int, var balance: Double) {

    abstract fun pay(amount: Double): String

    abstract fun withdraw(amount: Double): String

    fun deposit(amount: Double) {
        balance += amount
    }

    companion object {
        const val TRANSACTION_SUCCESS = "Success"
        const val TRANSACTION_FAILURE = "Failure"
    }
}
