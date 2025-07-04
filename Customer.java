public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private double balance;

    public Customer(String name, String email, String phoneNumber, double balance) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
