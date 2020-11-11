package banking.cli.command;

import banking.cli.CommandLineInterface;
import banking.controller.AccountController;

import java.util.Scanner;

abstract class AbstractCommand {

    protected final AccountController controller;

    AbstractCommand(AccountController controller) {
        this.controller = controller;
    }

    protected long getAccountId(String prompt) {
        long accountId = -1;
        boolean success;
        do {
            String input = CommandLineInterface.userInput(prompt);
            Scanner scanner = new Scanner(input);
            success = scanner.hasNextLong();
            if (success) {
                accountId = scanner.nextLong();
            } else {
                System.out.println("Неверный формат номера!");
            }
        } while (!success);

        return accountId;
    }

    abstract void doSomeWork();

    public void execute() {
        long accountId = getAccountId("Номер счета");
        if (controller.logIntoAccountById(accountId)) {
            doSomeWork();
            controller.logOut();
        } else {
            System.out.println("Счет с номером " + accountId + " не найден!");
        }
    }
}