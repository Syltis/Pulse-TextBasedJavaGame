package GUI;

/*
Has edited this:
- Kristoffer
*/

import Interfaces.INewGame;
import Interfaces.IPlayerBeing;
import Interfaces.IGameWindowPrint;
import Managers.CommandControl;
import Managers.StringUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CommandListener implements ActionListener {

    private IGameWindowPrint IGameWindowPrint;
    private INewGame INewGame;
    private IPlayerBeing IPlayerBeing;
    private int blankCounter;

    CommandListener(IGameWindowPrint IGameWindowPrint, INewGame INewGame, IPlayerBeing IPlayerBeing)
    {
        this.IGameWindowPrint = IGameWindowPrint;
        this.INewGame = INewGame;
        this.IPlayerBeing = IPlayerBeing;
    }

    // This is called then the player enters a command
    public void  actionPerformed(ActionEvent e) {
            String input = IGameWindowPrint.getInputAreaTextField();
            if (input.length() > 0) {
                // Print in the inputAreaTextField (log)
                IGameWindowPrint.printCommandToLog(input);
                // Send to playerInput.
                input = StringUtilities.cleanString(input);
                new CommandControl(input, INewGame.getActiveScenario(), IGameWindowPrint, INewGame, IPlayerBeing);
                IGameWindowPrint.setInputAreaTextField("");
                blankCounter = 0;
            }

        // These activate when the input is empty
        else if (blankCounter < 1) {
            IGameWindowPrint.printResponseToLog("You should make a choice.");
            blankCounter++;
        } else if (blankCounter == 1) {
            IGameWindowPrint.printResponseToLog("Choice it up my dude.");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 2) {
            IGameWindowPrint.printResponseToLog("Come on, do something.");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 3) {
            IGameWindowPrint.printResponseToLog(":(");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 4) {
            IGameWindowPrint.printResponseToLog(">:(");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 5) {
            new PopUp("You're an idiot", "Seriously");
            blankCounter = blankCounter + 1;
        } else {
            IGameWindowPrint.printResponseToLog("...");
            blankCounter = blankCounter + 1;
        }
    }
}
