package banking.cli;

import banking.cli.command.*;
import banking.controller.AccountController;

public enum OperationType {
    WITHDRAW() {
        @Override
        public void execute(AccountController controller) {
            new Withdraw(controller).execute();
        }
    },
    BALANCE() {
        @Override
        public void execute(AccountController controller) {
            new Balance(controller).execute();
        }
    },
    DEPOSIT() {
        @Override
        public void execute(AccountController controller) {
            new Deposit(controller).execute();
        }
    },
    TRANSFER() {
        @Override
        public void execute(AccountController controller) {
            new Transfer(controller).execute();
        }
    },
    RENAME() {
        @Override
        public void execute(AccountController controller) {
            new Rename(controller).execute();
        }
    },
    EXIT() {
        @Override
        public void execute(AccountController controller) {
            System.out.println("Работа завершена.");
        }
    };

    abstract public void execute(AccountController controller);
}
