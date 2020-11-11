package banking.cli.command;

import banking.cli.CommandLineInterface;
import banking.controller.AccountController;

import java.util.Scanner;

public class Transfer extends AbstractCommand {
    public Transfer(AccountController controller) {
        super(controller);
    }

    @Override
    void doSomeWork() {
        var account = controller.getActiveAccount();
        System.out.println("Остаток на счете: " + account.getBalance());

        long payeeId = getAccountId("Номер счета получателя");
        String input = CommandLineInterface.userInput("Сумма к переводу");
        Scanner scanner = new Scanner(input);
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            if (controller.transfer(payeeId, amount)) {
                System.out.println("Остаток после перевода: " + account.getBalance());
            } else {
                System.out.println("Недостаточно средств!");
            }
        } else {
            System.out.println("Неверный формат суммы!");
        }
    }
}