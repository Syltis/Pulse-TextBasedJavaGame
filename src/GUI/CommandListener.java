package GUI;

/*
Has edited this:
- Kristoffer
*/

import Interfaces.Choosable;
import Interfaces.Playable;
import Interfaces.Printable;
import Managers.CommandControl;
import Managers.StringUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CommandListener implements ActionListener {

    private Printable printable;
    private Choosable choosable;
    private Playable playable;
    private int blankCounter;

    CommandListener(Printable printable, Choosable choosable, Playable playable)
    {

        this.printable = printable;
        this.choosable = choosable;
        this.playable = playable;
    }

    // This is called then the player enters a command
    public void  actionPerformed(ActionEvent e) {
            String input = printable.getInputAreaTextField();
            if (input.length() > 0) {
                // Print in the inputAreaTextField (log)
                printable.printCommandToLog(input);
                // Send to playerInput.
                input = StringUtilities.cleanString(input);
                new CommandControl(input, choosable.getActiveScenario(), printable, choosable, playable);
                printable.setInputAreaTextField("");
                blankCounter = 0;
            }

        // These activate when the input is empty
        else if (blankCounter < 1) {
            printable.printResponseToLog("You should make a choice.");
            blankCounter++;
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
            new PopUp("You're an idiot", "Seriously");
            blankCounter = blankCounter + 1;
        } else {
            printable.printResponseToLog("...");
            blankCounter = blankCounter + 1;
        }
    }
}
