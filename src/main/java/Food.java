import java.io.Serializable;

//
class Food implements Serializable {
    public enum FoodType {
        Sandwich(50), Pasta(60), Noodles(70), Coke(30);

        int price;

        FoodType(int price){
            this.price = price;
        }

        public int getPrice(){
            return price;
        }
    };


    private final int quantity;
    private FoodType item;

    Food(FoodType item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    public String getItemName(){return item.name();}

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return quantity * item.price;
    }

    public static FoodType getFoodType(int index){
        return FoodType.values()[index-1];
    }
}
