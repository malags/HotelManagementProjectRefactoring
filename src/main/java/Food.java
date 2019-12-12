import ch.usi.si.codelounge.jsicko.Contract;

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
        @Ensures("price_per_item_is_correct")
        public int getPrice() {
            return price;
        }

        @Pure
        boolean price_per_item_is_correct(int returns){
            return returns == this.price &&
                    (returns == 50 || returns == 60 || returns == 70 || returns == 30);
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

    Food(){  //violates invariant for jsicko
        this.item = FoodType.Sandwich;
        this.quantity = Integer.MIN_VALUE;
    }

    /**
     * @return the type of food.
     */
    @Pure
    @Ensures("ItemName_is_valid")
    public String getItemName() {
        return item.name();
    }

    @Pure
    protected boolean ItemName_is_valid(String returns){
        return
                returns.equals("Sandwich")  ||
                returns.equals("Pasta")     ||
                returns.equals("Noodles")   ||
                returns.equals("Coke");
    }

    /**
     * @return the quantity of food.
     */
    @Pure
    @Ensures("positive_integer_returned")
    public int getQuantity() {
        return quantity;
    }

    @Pure
    boolean positive_integer_returned(int returns){
        return returns > 0;
    }

    /**
     * @return the price of food.
     */
    @Pure
    @Ensures("positive_return")
    public float getPrice() {
        return quantity * item.price;
    }

    @Pure
    boolean positive_return(float returns){
        return returns > 0;
    }

    /**
     * @param index
     * @return the type of food.
     */
    @Pure
    @Requires("foodType_index_is_valid")
    @Ensures("returns")
    public static FoodType getFoodType(int index) {
        return FoodType.values()[index - 1];
    }


    @Pure
    protected boolean foodType_index_is_valid(int index){
        return index >= 1 && index <= FoodType.values().length;
    }

}
