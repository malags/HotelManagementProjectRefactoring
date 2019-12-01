import java.io.Serializable;

/**
 * Class representing the food object.
 */
class Food implements Serializable {

    /**
     * Enum that represents food type whit price.
     */
    public enum FoodType {
        Sandwich(50), Pasta(60), Noodles(70), Coke(30);

        int price;

        /**
         * @param price
         */
        FoodType(int price) {
            this.price = price;
        }

        /**
         * @return the price of the corresponding food.
         */
        public int getPrice() {
            return price;
        }
    }


    /**
     * Fields that represent the food object.
     */
    private final int quantity;
    private FoodType item;


    /**
     * @param item
     * @param quantity
     */
    Food(FoodType item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * @return the type of food.
     */
    public String getItemName() {
        return item.name();
    }

    /**
     * @return the quantity of food.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return the price of food.
     */
    public float getPrice() {
        return quantity * item.price;
    }

    /**
     * @param index
     * @return the type of food.
     */
    public static FoodType getFoodType(int index) {
        return FoodType.values()[index - 1];
    }
}
