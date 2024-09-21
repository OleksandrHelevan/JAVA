public class Product implements Comparable<Product>{
    String name;
    Double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Product o) {
        return price.compareTo(o.price);
    }

    @Override
    public String toString() {
        return "Product [" + name + " " + price + "]";
    }
}
