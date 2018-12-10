package GUI;

/*
Has edited this:
- Kristoffer
*/

import Interfaces.IActiveScenario;
import Interfaces.IPlayerBeing;
import Interfaces.IGameWindowPrinter;
import Managers.CommandControl;
import Managers.StringUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CommandListener implements ActionListener {

    private IGameWindowPrinter IGameWindowPrinter;
    private IActiveScenario IActiveScenario;
    private IPlayerBeing IPlayerBeing;
    private int blankCounter;

    CommandListener(IGameWindowPrinter IGameWindowPrinter, IActiveScenario IActiveScenario, IPlayerBeing IPlayerBeing)
    {
        this.IGameWindowPrinter = IGameWindowPrinter;
        this.IActiveScenario = IActiveScenario;
        this.IPlayerBeing = IPlayerBeing;
    }

    // This is called then the player enters a command
    public void  actionPerformed(ActionEvent e) {
            String input = IGameWindowPrinter.getInputAreaTextField();
            if (input.length() > 0) {
                // Print in the inputAreaTextField (log)
                IGameWindowPrinter.printCommandToLog(input);
                // Send to playerInput.
                input = StringUtilities.cleanString(input);
                new CommandControl(input, IActiveScenario.getActiveScenario(), IGameWindowPrinter, IActiveScenario, IPlayerBeing);
                IGameWindowPrinter.setInputAreaTextField("");
                blankCounter = 0;
            }

        // These activate when the input is empty
        else if (blankCounter < 1) {
            IGameWindowPrinter.printResponseToLog("You should make a choice.");
            blankCounter++;
        } else if (blankCounter == 1) {
            IGameWindowPrinter.printResponseToLog("Choice it up my dude.");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 2) {
            IGameWindowPrinter.printResponseToLog("Come on, do something.");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 3) {
            IGameWindowPrinter.printResponseToLog(":(");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 4) {
            IGameWindowPrinter.printResponseToLog(">:(");
            blankCounter = blankCounter + 1;
        } else if (blankCounter == 5) {
            new PopUp("You're an idiot", "Seriously");
            blankCounter = blankCounter + 1;
        } else {
            IGameWindowPrinter.printResponseToLog("...");
            blankCounter = blankCounter + 1;
        }
    }
}
