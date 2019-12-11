import ch.usi.si.codelounge.jsicko.Contract;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

import java.io.Serializable;

/**
 * Class representing the food object.
 */
class Food implements Serializable , Contract {


    @Pure
    @Invariant
    protected boolean quantity_is_bigger_than_0(){
        return this.quantity >= 1;
    }


    /**
     * Enum that represents food type whit price.
     */
    public enum FoodType {
        Sandwich(50), Pasta(60), Noodles(70), Coke(30);

        @Invariant
        @Pure
        protected boolean price_is_bigger_than_0(){
            return this.price > 0;
        }

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
        @Pure
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
    @Pure
    public String getItemName() {
        return item.name();
    }

    /**
     * @return the quantity of food.
     */
    @Pure
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return the price of food.
     */
    @Pure
    public float getPrice() {
        return quantity * item.price;
    }

    /**
     * @param index
     * @return the type of food.
     */
    @Pure
    @Requires("foodType_index_is_valid")
    @EnsuresNonNull("returns")
    public static FoodType getFoodType(int index) {
        return FoodType.values()[index - 1];
    }


    @Pure
    protected boolean foodType_index_is_valid(int index){
        return index >= 1 && index <= FoodType.values().length;
    }

}
