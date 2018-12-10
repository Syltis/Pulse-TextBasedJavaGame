package GUI;

import Interfaces.IPrinter;
import Models.Item;
import Models.Scenario;
import Models.MovementCommand;

import java.util.List;

// CLASS NOT FINISHED AND NOT IN USE
public class Printer implements IPrinter {

    GameWindow gameWindow;

    public Printer(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public IPrinter getPrintable() {
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
    public void printScenarioToGameArea(String title, String descrption) {
        gameWindow.gameTextArea.append(" " + title + "\n");
        gameWindow.gameTextArea.append("> " + descrption + "\n");
        gameWindow.gameTextArea.setCaretPosition(gameWindow.gameTextArea.getDocument().getLength());
    }

    @Override
    public void printInventoryToGameArea(List<Item> inventory) {

    }

    public void printToGameArea(String text, boolean hasDash) {
        if (hasDash) {
            gameWindow.gameTextArea.append("- " + text + "\n");
            gameWindow.gameTextArea.setCaretPosition(gameWindow.gameTextArea.getDocument().getLength());
        }
        else {
            gameWindow.gameTextArea.append(text + "\n");
            gameWindow.gameTextArea.setCaretPosition(gameWindow.gameTextArea.getDocument().getLength());
        }
    }

    public void printToSidebarArea(String text) {
        gameWindow.sidebarTextArea.append("> " + text + "\n");
        gameWindow.sidebarTextArea.setCaretPosition(gameWindow.sidebarTextArea.getDocument().getLength());
    }

    public void feedSideBar(Scenario activeScenario) {
        printToSidebarArea("MOVEMENT:");
        if (activeScenario.getAvailableMovementCommands() != null && activeScenario.getAvailableMovementCommands().length > 0) {
            for (MovementCommand aMovementCommand : activeScenario.getAvailableMovementCommands()) {
                printToSidebarArea(aMovementCommand.getCommand());
            }
        }
        //printToSidebarArea(activeScenario.getAvailableMovementCommands().toString().replaceAll("[-!@#$%^&*().?\":{}|<>0-9+/'=]+", ""));
        printToSidebarArea("ACTIONS:");
        if (activeScenario.getAvailableActionCommands() != null && activeScenario.getAvailableActionCommands().length > 0){
            printToSidebarArea(activeScenario.getAvailableActionCommands().toString().replaceAll("[-!@#$%^&*().?\":{}|<>0-9+/'=\\[\\]]+", ""));
        }
        printToSidebarArea("COMBAT:");
        if (activeScenario.getAvailableCombatCommands() != null && activeScenario.getAvailableCombatCommands().length > 0) {
            printToSidebarArea(activeScenario.getAvailableCombatCommands().toString().replaceAll("[-!@#$%^&*().?\":{}|<>0-9+/'=\\[\\]]+", ""));
        }
    }

    public String getInputAreaTextField() {
        if (gameWindow.inputAreaTextField != null && !gameWindow.getInputAreaTextField().isEmpty())
            return gameWindow.inputAreaTextField.getText();
        else {
            System.out.println("textfield empty");
            return "null";

        }
    }


    public void setInputAreaTextField(String text) {
        gameWindow.inputAreaTextField.setText(text);
    }

    public void clearSideBarArea() {
        gameWindow.sidebarTextArea.setText("");
    }
}
