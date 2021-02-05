package exercise2

import kotlin.random.Random

class Bank {

    private val cards = mutableMapOf<Int, Card>()

    fun createDebitCard(balance: Double): Int = with(generateCardId()) {
        cards[this] = DebitCard(this, balance)
        return@with this
    }

    fun createCreditCard(balance: Double, creditLimit: Double) = with(generateCardId()) {
        cards[this] = CreditCard(this, balance, creditLimit)
        return@with this
    }

    fun pay(cardID: Int, amount: Double): String = cards[cardID]?.pay(amount) ?: Card.TRANSACTION_FAILURE

    fun withdraw(cardID: Int, amount: Double) = cards[cardID]?.withdraw(amount) ?: Card.TRANSACTION_FAILURE

    fun deposit(cardID: Int, amount: Double) = cards[cardID]?.deposit(amount) ?: Card.TRANSACTION_FAILURE

    fun consultBalance(cardID: Int): Double? = cards[cardID]?.balance

    private fun generateCardId(): Int {
        var cardID: Int
        do {
            cardID = Random.nextInt()
        } while (cardID in cards.keys)

        return cardID
    }

    companion object {
        const val CREDIT_CARD_REWARD = 0.1
    }
}
