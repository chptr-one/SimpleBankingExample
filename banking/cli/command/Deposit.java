package banking.cli.command;

import banking.cli.CommandLineInterface;
import banking.controller.AccountController;

import java.util.Scanner;

public class Deposit extends AbstractCommand {
    public Deposit(AccountController controller) {
        super(controller);
    }

    @Override
    void doSomeWork() {
        var account = controller.getActiveAccount();
        System.out.println("Остаток на счете: " + account.getBalance());

        String input = CommandLineInterface.userInput("Сумма вклада");
        Scanner scanner = new Scanner(input);
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            if (controller.deposit(amount)) {
                System.out.println("Остаток после вклада: " + account.getBalance());
            } else {
                System.out.println("Сумма вклада не может быть меньше 0");
            }
        } else {
            System.out.println("Неверный формат суммы!");
        }
    }
}
