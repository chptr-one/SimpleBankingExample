package banking.cli;

import banking.controller.AccountController;

import java.util.EnumSet;
import java.util.Scanner;

public class CommandLineInterface {

    private static final Scanner scanner = new Scanner(System.in);
    private final AccountController controller;
    private final EnumSet<OperationType> operationTypes;

    public CommandLineInterface(AccountController controller) {
        this.controller = controller;
        this.operationTypes = EnumSet.allOf(OperationType.class);
    }

    public static String userInput(String prompt) {
        System.out.print(prompt + " > ");
        return scanner.nextLine();
    }

    public void run() {
        String command;
        do {
            printMenu();
            command = userInput("Введите тип операции").toUpperCase();
            String finalCommand = command; // captured parameter in lambda must be effectively final
            operationTypes.stream()
                    .filter(op -> op.name().equals(finalCommand))
                    .findFirst()
                    .ifPresentOrElse(
                            opType -> opType.execute(controller),
                            () -> System.out.println("Неверная операция!")
                    );
            System.out.println();
        } while (!"EXIT".equals(command));
    }

    private void printMenu() {
        System.out.println("Типы операций: " + operationTypes + ".");
        System.out.println("Для выхода введите [EXIT].");
    }
}
