import org.junit.Test;

public class FoodTest {
    @Test
    public void foodTypeRightOrder() {
        Food.FoodType[] allFoodTypes = Food.FoodType.values();
        assert (allFoodTypes[0] == Food.FoodType.Sandwich);
        assert (allFoodTypes[1] == Food.FoodType.Pasta);
        assert (allFoodTypes[2] == Food.FoodType.Noodles);
        assert (allFoodTypes[3] == Food.FoodType.Coke);
    }

    @Test
    public void foodTypeRightPrice() {
        assert (Food.FoodType.Sandwich.getPrice() == 50);
        assert (Food.FoodType.Pasta.getPrice() == 60);
        assert (Food.FoodType.Noodles.getPrice() == 70);
        assert (Food.FoodType.Coke.getPrice() == 30);
    }

    @Test
    public void foodGetQuantity1() {
        Food food = new Food(Food.FoodType.Pasta, 1);
        assert (food.getQuantity() == 1);
    }

    @Test
    public void foodGetQuantity2() {
        Food food = new Food(Food.FoodType.Pasta, 2);
        assert (food.getQuantity() == 2);
    }

    @Test
    public void foodPriceScalesWithQuantity() {
        Food food1 = new Food(Food.FoodType.Pasta, 1);
        assert (food1.getPrice() == Food.FoodType.Pasta.getPrice());
        Food food2 = new Food(Food.FoodType.Pasta, 2);
        assert (food2.getPrice() == 2 * Food.FoodType.Pasta.getPrice());
    }

    @Test
    public void getItemName() {
        Food food = new Food(Food.FoodType.Pasta, 1);
        assert (food.getItemName().equals("Pasta"));
    }
}
