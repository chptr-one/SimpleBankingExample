package banking.cli.command;

import banking.cli.CommandLineInterface;
import banking.controller.AccountController;

import java.util.Scanner;

public class Withdraw extends AbstractCommand {
    public Withdraw(AccountController controller) {
        super(controller);
    }

    @Override
    void doSomeWork() {
        var account = controller.getActiveAccount();
        System.out.println("Остаток на счете: " + account.getBalance());

        String input = CommandLineInterface.userInput("Сумма к выводу");
        Scanner scanner = new Scanner(input);
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            if (controller.withdraw(amount)) {
                System.out.println("Остаток после вывода: " + account.getBalance());
            } else {
                System.out.println("Недостаточно средств!");
            }
        } else{
            System.out.println("Неверный формат суммы!");
        }
    }
}
