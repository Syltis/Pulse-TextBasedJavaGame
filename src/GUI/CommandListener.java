package GUI;

/*
Has edited this:
- Kristoffer
*/

import Interfaces.INewGame;
import Interfaces.IPlayerBeing;
import Interfaces.IPrinter;
import Managers.CommandControl;
import Managers.StringUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CommandListener implements ActionListener {

    private IPrinter IPrinter;
    private INewGame INewGame;
    private IPlayerBeing IPlayerBeing;
    private int blankCounter;

    CommandListener(IPrinter IPrinter, INewGame INewGame, IPlayerBeing IPlayerBeing)
    {
        this.IPrinter = IPrinter;
        this.INewGame = INewGame;
        this.IPlayerBeing = IPlayerBeing;
    }

    // This is called then the player enters a command
    public void  actionPerformed(ActionEvent e) {
            String input = IPrinter.getInputAreaTextField();
            if (input.length() > 0) {
                // Print in the inputAreaTextField (log)
                IPrinter.printCommandToLog(input);
                // Send to playerInput.
                input = StringUtilities.cleanString(input);
                new CommandControl(input, INewGame.getActiveScenario(), IPrinter, INewGame, IPlayerBeing);
                IPrinter.setInputAreaTextField("");
                blankCounter = 0;
            }

        // These activate when the input is empty
        else if (blankCounter < 1) {
            IPrinter.printResponseToLog("You should make a choice.");
            blankCounter++;
        } else if (blankCounter == 1) {
            IPrinter.printResponseToLog("Choice it up my dude.");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 2) {
            IPrinter.printResponseToLog("Come on, do something.");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 3) {
            IPrinter.printResponseToLog(":(");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 4) {
            IPrinter.printResponseToLog(">:(");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 5) {
            new PopUp("You're an idiot", "Seriously");
            blankCounter = blankCounter + 1;
        } else {
            IPrinter.printResponseToLog("...");
            blankCounter = blankCounter + 1;
        }
    }
}
