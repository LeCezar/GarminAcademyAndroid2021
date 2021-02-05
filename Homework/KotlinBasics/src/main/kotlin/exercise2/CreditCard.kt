package exercise2

class CreditCard(cardId: Int, balance: Double, private val creditLimit: Double) : Card(cardId, balance) {

    override fun pay(amount: Double): String =

        if (balance + creditLimit - amount >= 0) {
            balance = balance - amount + amount * Bank.CREDIT_CARD_REWARD
            TRANSACTION_SUCCESS
        } else {
            TRANSACTION_FAILURE
        }

    override fun withdraw(amount: Double): String =

        if (balance + creditLimit - amount >= 0) {
            balance -= amount
            TRANSACTION_SUCCESS
        } else {
            TRANSACTION_FAILURE
        }
}
