import java.util.*

open class Item(val name: String, val price: Double)
// Food ve cloth sınıfları tarafından inherit edilebilmesi için open keyword'ünü kullandık
class Food(name: String, price: Double, val weight: String) : Item(name, price)

class Cloth(name: String, price: Double, val type: String) : Item(name, price)

class ShoppingList {
    private val items = mutableListOf<Item>()

    fun addItem() {
        val scanner = Scanner(System.`in`)
        println("Select type of product:")
        println("1. Food")
        println("2. Cloth")
        var choice = scanner.nextInt()
        while (choice != 1 && choice != 2) {
            println("Invalid choice! Please enter 1 for Food or 2 for Cloth:")
            // Eğer 1 veya 2 dışında rakam girilirse kabul etmiyor
            choice = scanner.nextInt()
        }

        when (choice) {
            1 -> {
                println("Enter name of the food:")
                val foodName = readLine()!!
                println("Enter price of the food:")
                val price = scanner.nextDouble()
                println("Enter weight of the food:")
                val weight = readLine()!!
                val food = Food(foodName, price, weight)
                items.add(food)
                println("${food.name} added to the list.")
            }
            2 -> {
                println("Enter name of the cloth:")
                val clothName = readLine()!!
                println("Enter price of the cloth:")
                val price = scanner.nextDouble()
                println("Enter type of the cloth:")
                val type = readLine()!!
                val cloth = Cloth(clothName, price, type)
                items.add(cloth)
                println("${cloth.name} added to the list.")
            }
        }
    }

    fun displayItems() {
        if (items.isEmpty()) {
            println("No items in the list.")
        } else {
            for ((index, item) in items.withIndex()) {
                println("Item ${index + 1}: ${item.name}, Price: ${item.price}")
                if (item is Food) {
                    println("Weight: ${item.weight}")
                } else if (item is Cloth) {
                    println("Type: ${item.type}")
                }
            }
        }
    }

    fun deleteItem() {
        val scanner = Scanner(System.`in`)
        if (items.isEmpty()) {
            println("No items in the list to delete.")
        } else {
            println("Enter the number of the item to delete:")
            val index = scanner.nextInt() - 1
            if (index in 0 until items.size) {
                val deletedItem = items.removeAt(index)
                println("${deletedItem.name} removed from the list.")
            } else {
                println("Invalid item number.")
            }
        }
    }
}

fun main() {
    val shoppingList = ShoppingList()
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\nMenu:")
        println("1. Add Item")
        println("2. Display Item")
        println("3. Delete Item")
        println("4. Exit")
        print("Enter your choice: ")
        when (val choice = scanner.nextInt()) {
            1 -> shoppingList.addItem()
            2 -> shoppingList.displayItems()
            3 -> shoppingList.deleteItem()
            4 -> {
                println("Exiting the application.")
                return
            }
            else -> println("Invalid choice! Please enter a number from 1 to 4.")
        }
    }
}
