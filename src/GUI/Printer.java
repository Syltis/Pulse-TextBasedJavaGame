package GUI;

import Interfaces.Printable;
import Models.ChoiceV2;
import Models.MovementCommand;

// METHOD NOT FINISHED AND NOT IN USE
public class Printer implements Printable {

    GameWindow gameWindow;

    public Printer(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public Printable getPrintable() {
        return Printer.this;
    }
    // Prints  player command to inputAreaTextField (log)
    public void printCommandToLog(String text) {

        gameWindow.inputAreaTextArea.append("> " + text + "\n");
        gameWindow.inputAreaTextArea.setCaretPosition(gameWindow.inputAreaTextArea.getDocument().getLength());
    }

    public void printResponseToLog(String text) {

        gameWindow.inputAreaTextArea.append("- " + text + "\n");
        gameWindow.inputAreaTextArea.setCaretPosition(gameWindow.inputAreaTextArea.getDocument().getLength());
    }

    // Prints to the gameTextArea.
    public void printResponseToGameArea(String title, String descrption) {
        gameWindow.gameTextArea.append(" " + title + "\n");
        gameWindow.gameTextArea.append("> " + descrption + "\n");
        gameWindow.gameTextArea.setCaretPosition(gameWindow.gameTextArea.getDocument().getLength());
    }

    public void printCommandToGameArea(String text) {
        gameWindow.gameTextArea.append("- " + text + "\n" + "\n");
        gameWindow.gameTextArea.setCaretPosition(gameWindow.gameTextArea.getDocument().getLength());
    }

    public void printToSidebarArea(String text) {
        gameWindow.sidebarTextArea.append("> " + text + "\n");
        gameWindow.sidebarTextArea.setCaretPosition(gameWindow.sidebarTextArea.getDocument().getLength());
    }

    public void feedSideBar(ChoiceV2 activeChoice) {
        printToSidebarArea("MOVEMENT:");
        if (activeChoice.getAvailableMovementCommands() != null && activeChoice.getAvailableMovementCommands().length > 0) {
            for (MovementCommand aMovementCommand : activeChoice.getAvailableMovementCommands()) {
                printToSidebarArea(aMovementCommand.getMovementCommand());
            }
        }
        //printToSidebarArea(activeChoice.getAvailableMovementCommands().toString().replaceAll("[-!@#$%^&*().?\":{}|<>0-9+/'=]+", ""));
        printToSidebarArea("ACTIONS:");
        if (activeChoice.getAvailableActionCommands() != null && !activeChoice.getAvailableActionCommands().isEmpty()){
            printToSidebarArea(activeChoice.getAvailableActionCommands().toString().replaceAll("[-!@#$%^&*().?\":{}|<>0-9+/'=\\[\\]]+", ""));
        }
        printToSidebarArea("COMBAT:");
        if (activeChoice.getAvailableCombatCommands() != null && !activeChoice.getAvailableCombatCommands().isEmpty()) {
            printToSidebarArea(activeChoice.getAvailableCombatCommands().toString().replaceAll("[-!@#$%^&*().?\":{}|<>0-9+/'=\\[\\]]+", ""));
        }
    }

    public String getInputAreaTextField() {
        if (gameWindow.inputAreaTextField != null && !gameWindow.getInputAreaTextField().isEmpty())
            return gameWindow.inputAreaTextField.getText();
        else {
            System.out.println("textfield empty");
            String nullResult = "null";
            return nullResult;
        }
    }


    public void setInputAreaTextField(String text) {
        gameWindow.inputAreaTextField.setText(text);
    }

    public void clearSideBarArea() {
        gameWindow.sidebarTextArea.setText("");
    }
}
