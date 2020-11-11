package banking.model;

import java.util.Objects;

public class Account {
    private final Long id;
    private String holderName;
    private double balance;

    public Account(Long id, String holderName, double balance) {
        this.id = id; // must be unique
        this.holderName = holderName;
        this.balance = balance;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
