package task3;

public class Item {
    private final String itemName;
    private final int itemPrice;

    public Item(String itemName, int itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public void getItem(){
        System.out.println(itemName);
        System.out.println(itemPrice);
    }

    @Override
    public String toString() {
        return "{" + itemName + " " + itemPrice + "}" ;
    }
}
