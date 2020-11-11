package banking.repository;

import banking.model.Account;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class TextFileRepository implements Repository {

    private static final Path FILE_PATH = Path.of(
            System.getProperty("user.dir") + File.separator +
                    "accounts.txt"
    );

    private static final Repository INSTANCE = new TextFileRepository();

    public static Repository getInstance() {
        return INSTANCE;
    }

    // only unique by 'id' accounts allowed
    private Set<Account> accounts;

    private TextFileRepository() {
        try (var fileReader = Files.newBufferedReader(FILE_PATH)) {
            accounts = fileReader.lines()
                    .map(newAccountFromString())
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            // creates a new file with 10 empty accounts if file can not be read
            Logger logger = Logger.getLogger(TextFileRepository.class.getName());
            logger.warning("File " + FILE_PATH + " not found. New file of 10 empty Accounts was created.");
            accounts = LongStream.rangeClosed(1, 10)
                    .mapToObj(l -> new Account(l, "", 0.0))
                    .collect(Collectors.toSet());
            try {
                Files.createFile(FILE_PATH);
                flush();
            } catch (IOException io) {
                throw new RuntimeException("Error creating file " + FILE_PATH, io);
            }
        }
    }

    private Function<String, Account> newAccountFromString() {
        return string -> {
            var items = string.split(";");
            var id = Long.parseLong(items[0]);
            var holderName = items[1];
            var amount = Double.parseDouble(items[2]);
            return new Account(id, holderName, amount);
        };
    }

    @Override
    public Optional<Account> getById(long id) {
        return accounts.stream()
                .filter(account -> account.getId() == id)
                .findFirst();
    }

    @Override
    public boolean update(Account account) {
        if (!accounts.contains(account)) {
            return false;
        }
        flush();
        return true;
    }

    // rewrites the entire file. Stupid but simple
    private void flush() {
        try (var writer = new PrintWriter(Files.newBufferedWriter(FILE_PATH))) {
            accounts.forEach(account -> {
                String record = account.getId() + ";" +
                        account.getHolderName() + ";" +
                        account.getBalance();
                writer.println(record);
            });
        } catch (IOException e) {
            throw new RuntimeException("Error writing " + FILE_PATH, e);
        }
    }
}
