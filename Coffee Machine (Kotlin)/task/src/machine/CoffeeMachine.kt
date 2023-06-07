package machine

class CoffeeMachine {
    private var waterLevel: Int = 400
    private var milkLevel: Int = 540
    private var coffeeBeansLevel: Int = 120
    private var disposableCupNumber: Int = 9
    lateinit var limitingIngredients: String
    private var money: Int = 550

    fun fillWaterTank(level: Int) {
        waterLevel += level
    }

    fun fillMilkTank(level: Int) {
        milkLevel += level
    }

    fun fillCoffeeBeanTank(level: Int) {
        coffeeBeansLevel += level
    }

    fun fillDisposableCups(level: Int) {
        disposableCupNumber += level
    }

    fun calculateMaxCoffeeNumber(coffee: Coffee): Int {

        val maxCoffeeByWater = if (coffee.waterRequirement != 0) waterLevel / coffee.waterRequirement else Int.MAX_VALUE
        val maxCoffeeByMilk = if (coffee.milkRequirement != 0) milkLevel / coffee.milkRequirement else Int.MAX_VALUE
        val maxCoffeeByCoffeeBeans =
            if (coffee.coffeeBeanRequirement != 0) coffeeBeansLevel / coffee.coffeeBeanRequirement else Int.MAX_VALUE

        limitingIngredients =
            when (minOf(maxCoffeeByWater, maxCoffeeByMilk, maxCoffeeByCoffeeBeans, disposableCupNumber)) {
                maxCoffeeByWater -> "water"
                maxCoffeeByMilk -> "milk"
                maxCoffeeByCoffeeBeans -> "coffee beans"
                disposableCupNumber -> "disposable cups"
                else -> "No limiting"
            }
        return minOf(maxCoffeeByWater, maxCoffeeByMilk, maxCoffeeByCoffeeBeans, disposableCupNumber)

    }


    fun makeCoffee(coffee: Coffee) {
        waterLevel -= coffee.waterRequirement
        milkLevel -= coffee.milkRequirement
        coffeeBeansLevel -= coffee.coffeeBeanRequirement
        disposableCupNumber--
        money += coffee.cost
    }

    fun getMoney() {
        println("I gave you $$money")
        money = 0
    }

    override fun toString(): String {
        return """
             
            The coffee machine has:
            $waterLevel ml of water
            $milkLevel ml of milk
            $coffeeBeansLevel g of coffee beans
            $disposableCupNumber disposable cups
            $$money of money
            
        """.trimIndent()

    }

}