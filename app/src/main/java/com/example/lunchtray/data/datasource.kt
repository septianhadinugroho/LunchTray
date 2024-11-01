package com.example.lunchtray.data

import com.example.lunchtray.model.OrderItem

class DataSource{
    companion object{
        val entree = listOf<OrderItem>(
            OrderItem(
                title = "Cauliflower",
                description = "Whole cauliflower, brined, roasted, and deep fried",
                price = 7.0,

            ),
            OrderItem(
                title = "Three Bean Chili",
                description = "Black beans, red beans, kidney beans, slow cooked, topped with onion",
                price = 4.0,

            ),
            OrderItem(
                title = "Mushroom Pasta",
                description = "Penne pasta, mushrooms, basil, with plum tomatoes cooked in garlic and olive oil",
                price = 5.5,

            ),
            OrderItem(
                title = "Spicy Black Bean Skillet",
                description = "Seasonal vegetables, black beans, house spice blend, served with avocado and quick pickled onoins",
                price = 5.5,

            ),
        )

        val sideDish = listOf<OrderItem>(
            OrderItem(
                title = "Summer Salad",
                description = "Heirloom tomatoes,butter lettuce, peaches, avocado, balsamic dressing",
                price = 2.5,

            ),
            OrderItem(
                title = "Butternut Squash Soup",
                description = "Roasted butternut squash, roasted peppers, chili oil",
                price = 3.0,

            ),
            OrderItem(
                title = "Spicy Potatoes",
                description = "Marble potatoes,roasted, and fried in house spice blend",
                price = 2.0,

            ),
            OrderItem(
                title = "Coconut Rice",
                description = "Rice,coconut milk,lime and sugar",
                price = 1.5,

            ),
        )

        val accompaniments = listOf<OrderItem>(
            OrderItem(
                title = "Lunch Roll",
                description = "Fresh baked roll made in house",
                price = 0.5,

            ),
            OrderItem(
                title = "Mixed Berries",
                description = "Strawberries, blueberries, raspberries, and huckleberries ",
                price = 1.0,

            ),
            OrderItem(
                title = "Pickled Veggies",
                description = "Pickled cucumbers and carrots, made in house",
                price = 0.5,
            )
        )
    }
}