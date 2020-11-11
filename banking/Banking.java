package banking;

import banking.cli.CommandLineInterface;
import banking.controller.AccountController;
import banking.repository.TextFileRepository;

public class Banking {

    public static void main(String[] args) {
        var repo = TextFileRepository.getInstance();
        var controller = AccountController.getInstance(repo);
        var userInterface = new CommandLineInterface(controller);

        userInterface.run();
    }
}
