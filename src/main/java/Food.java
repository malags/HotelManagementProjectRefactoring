import java.io.Serializable;

/**
 *
 */
class Food implements Serializable {

    /**
     *
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
         * @return
         */
        public int getPrice() {
            return price;
        }
    }


    /**
     *
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
     * @return
     */
    public String getItemName() {
        return item.name();
    }

    /**
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return
     */
    public float getPrice() {
        return quantity * item.price;
    }

    /**
     * @param index
     * @return
     */
    public static FoodType getFoodType(int index) {
        return FoodType.values()[index - 1];
    }
}
