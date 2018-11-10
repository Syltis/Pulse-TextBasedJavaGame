package GUI;

import Interfaces.Printable;
import Managers.PlayerInput;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandListener implements ActionListener {

    private Printable printable;
    private int blankCounter;

    public CommandListener(Printable printable) {
        this.printable = printable;
    }

    public void  actionPerformed(ActionEvent e) {
        String input = printable.getInputAreaTextField();
        if (input.length() > 0) {
            // Print in the inputAreaTextField (log)
            printable.printCommandToLog(input);
            // Send to playerInput.
            PlayerInput playerInput = new PlayerInput();
            playerInput.receiveCommand(input);
            printable.setInputAreaTextField("");
            blankCounter = 0;
        }
        else if (blankCounter < 1) {
            printable.printResponseToLog("You should make a choice.");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 1) {
            printable.printResponseToLog("Choice it up my dude.");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 2) {
            printable.printResponseToLog("Come on, do something.");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 3) {
            printable.printResponseToLog(":(");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 4) {
            printable.printResponseToLog(">:(");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 5) {
            new popUp("You're an idiot", "Seriously");
            blankCounter = blankCounter + 1;
        } else {
            printable.printResponseToLog("...");
            blankCounter = blankCounter + 1;
        }
    }
}
