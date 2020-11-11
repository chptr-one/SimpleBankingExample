package banking.repository;

import banking.model.Account;

import java.util.Optional;

public interface Repository {

    Optional<Account> getById(long id);

    boolean update(Account account);
}
