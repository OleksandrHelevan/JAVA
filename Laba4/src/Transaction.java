public class Transaction {
    double amount;
    String category;

    public Transaction(double amount, String category) {
        this.amount = amount;
        this.category = category;
    }

    String getCategory() {
        return category;
    }

    double getAmount() {
        return amount;
    }

}
