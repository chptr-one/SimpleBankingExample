package banking.cli.command;

import banking.controller.AccountController;

public class Balance extends AbstractCommand {

    public Balance(AccountController controller) {
        super(controller);
    }

    @Override
    public void doSomeWork() {
        System.out.println("Остаток на счете: " + controller.balance());
    }
}
