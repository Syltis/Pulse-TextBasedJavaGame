package gui;

import interfaces.INewGame;
import interfaces.IPlayer;
import interfaces.IGameWindowPrint;
import managers.CommandControlHub;
import managers.StringUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Listener for the GameWindow/inputAreaTextField().
- Clean the user input and sends it to the CommandControlHub-class
 */
class InputListener implements ActionListener {

    private final IGameWindowPrint IGameWindowPrint;
    private final INewGame INewGame;
    private final IPlayer IPlayer;
    private int blankCounter;

    InputListener(IGameWindowPrint IGameWindowPrint, INewGame INewGame, IPlayer IPlayer)
    {
        this.IGameWindowPrint = IGameWindowPrint;
        this.INewGame = INewGame;
        this.IPlayer = IPlayer;
    }

    // This is called then the player enters a command
    public void  actionPerformed(ActionEvent e) {
            String input = IGameWindowPrint.getInputAreaTextField();
            if (input.length() > 0) {
                // Print in the inputAreaTextField (log)
                IGameWindowPrint.printCommandToLog(input);
                // Clean the string
                input = StringUtilities.cleanString(input);
                // Pass to commandControl together with the relevant interfaces.
                new CommandControlHub(INewGame.getActiveScenario(), input, IGameWindowPrint, INewGame, IPlayer);
                IGameWindowPrint.setInputAreaTextField("");
                blankCounter = 0;
            }

        // These are printed when the input is empty
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
