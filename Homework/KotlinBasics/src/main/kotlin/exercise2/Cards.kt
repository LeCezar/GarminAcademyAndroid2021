package exercise2

fun main() {
    val bank = Bank()
    val debitCardID = bank.createDebitCard(200.0)
    val creditCardID = bank.createCreditCard(200.0, 100.0)
    println(
        "Created debit card with id: $debitCardID" +
                "\n  and credit card with id: $creditCardID"
    )

    println("Pay 300 on DEBIT card gives message: ${bank.pay(debitCardID, 300.0)}")
    println("Pay 300 on CREDIT card gives message: ${bank.pay(creditCardID, 300.0)}")
    bank.pay(debitCardID, 150.0)
    println("Pay 150 on DEBIT result in a new balance of: ${bank.consultBalance(debitCardID)}")
    println(
        "After the 300 payment on the CREDIT card " +
                "the new balance is (including the generous bonus): ${bank.consultBalance(creditCardID)}"
    )

}
