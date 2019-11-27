import java.io.Serializable;

//
class Food implements Serializable {
    private final int itemno;
    private final int quantity;
    private float price;

    Food(int itemno, int quantity) {
        this.itemno = itemno;
        this.quantity = quantity;
        switch (itemno) {
            case 1:
                price = quantity * 50;
                break;
            case 2:
                price = quantity * 60;
                break;
            case 3:
                price = quantity * 70;
                break;
            case 4:
                price = quantity * 30;
                break;
        }
    }

    public int getItemno() {
        return itemno;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }
}
