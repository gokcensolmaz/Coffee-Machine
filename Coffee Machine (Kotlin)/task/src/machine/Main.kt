package machine

fun main() {
    val coffeeMachine = CoffeeMachine()
    while (true) {
        println("Write action (buy, fill, take): ")
        when (readln()) {
            "buy" -> buyCoffee(coffeeMachine)
            "fill" -> fillMachine(coffeeMachine)
            "take" -> coffeeMachine.getMoney()
            "remaining" -> println(coffeeMachine.toString())
            "exit" -> return
            else -> println("Write an action")
        }
    }

}

fun fillMachine(coffeeMachine: CoffeeMachine) {
    println("Write how many ml of water the coffee machine has:")
    coffeeMachine.fillWaterTank(readln().toInt())
    println("Write how many ml of milk the coffee machine has:")
    coffeeMachine.fillMilkTank(readln().toInt())
    println("Write how many grams of coffee beans the coffee machine has:")
    coffeeMachine.fillCoffeeBeanTank(readln().toInt())
    println("Write how many disposable cups you want to add:")
    coffeeMachine.fillDisposableCups(readln().toInt())
}

fun buyCoffee(coffeeMachine: CoffeeMachine) {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
    val choice = readln()
    val coffee = when (choice) {
        "1" -> Coffee("Espresso", 250, 0, 16, 4)

        "2" -> Coffee("Latte", 350, 75, 20, 7)

        "3" -> Coffee("Cappuccino", 200, 100, 12, 6)
        else -> {
            println("Invalid coffee choice. Please try again.")
            return
        }
    }
    if (coffeeMachine.calculateMaxCoffeeNumber(coffee) > 0) {
        println("I have enough resources, making you a coffee!")
        coffeeMachine.makeCoffee(coffee)
    } else {
        println("Sorry, not enough ${coffeeMachine.limitingIngredients}!")
    }
}