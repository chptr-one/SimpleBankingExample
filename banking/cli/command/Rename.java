package banking.cli.command;

import banking.cli.CommandLineInterface;
import banking.controller.AccountController;

public class Rename extends AbstractCommand {

    public Rename(AccountController controller) {
        super(controller);
    }

    @Override
    public void doSomeWork() {
        var account = controller.getActiveAccount();
        System.out.println("Текущее имя клиента: " + account.getHolderName());
        String newName = CommandLineInterface.userInput("Введите новое имя");
        controller.renameClient(newName);
        System.out.println("Новое имя клиента " + newName + " по счету " + controller.getActiveAccount().getId() + " установлено.");
    }
}
