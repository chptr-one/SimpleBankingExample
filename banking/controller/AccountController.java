package banking.controller;

import banking.model.Account;
import banking.repository.Repository;

public class AccountController {

    private static AccountController INSTANCE;

    public static AccountController getInstance(Repository repo) {
        if (INSTANCE == null) {
            INSTANCE = new AccountController(repo);
        }
        return INSTANCE;
    }

    private final Repository repo;
    private Account activeAccount;

    private AccountController(Repository repo) {
        this.repo = repo;
    }

    public Account getActiveAccount() {
        return activeAccount;
    }

    public boolean logIntoAccountById(long id) {
        var optionalAccount = repo.getById(id);
        if (optionalAccount.isPresent()) {
            activeAccount = optionalAccount.get();
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount >= 0) {
            double newBalance = activeAccount.getBalance() + amount;
            activeAccount.setBalance(newBalance);
            repo.update(activeAccount);
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        boolean result = false;
        if (amount > 0) {
            double newBalance = activeAccount.getBalance() - amount;
            if (newBalance >= 0) {
                activeAccount.setBalance(newBalance);
                repo.update(activeAccount);
                result = true;
            }
        }
        return result;
    }

    public boolean transfer(long payeeId, double amount) {
        boolean result = false;
        if (activeAccount.getBalance() >= amount) {
            var optionalPayee = repo.getById(payeeId);
            if (optionalPayee.isPresent()) {
                var payee = optionalPayee.get();
                payee.setBalance(payee.getBalance() + amount);
                activeAccount.setBalance(activeAccount.getBalance() - amount);
                repo.update(payee);
                repo.update(activeAccount);
                result = true;
            }
        }
        return result;
    }

    public double balance() {
        return activeAccount.getBalance();
    }

    public void renameClient(String name) {
        activeAccount.setHolderName(name);
        repo.update(activeAccount);
    }

    public void logOut() {
        activeAccount = null;
    }
}
