package exercise2

class DebitCard(cardID: Int, balance: Double) : Card(cardID, balance) {

    override fun pay(amount: Double): String =
        with(balance - amount) {
            if (this >= 0) {
                balance = this
                return@with TRANSACTION_SUCCESS
            } else {
                return@with TRANSACTION_FAILURE
            }
        }

    override fun withdraw(amount: Double): String = pay(amount)
}
