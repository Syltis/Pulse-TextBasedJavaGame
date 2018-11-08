package GUI;

import Managers.PlayerInput;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/*
Has edited this:
- Kristoffer
*/

public class GameWindow {

    private JTextArea sidebarTextArea;
    private JTextArea gameArea; // Am I sure about this? - kris
    private JTextArea inputAreaTextArea;
    private JTextField inputAreaTextField;
    private GridBagConstraints c;
    private PlayerInput playerInput;
    private int blankCounter = 0;

     public GameWindow() {
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        int hGap = 5;
        int vGap = 5;
        c.insets = new Insets(hGap, vGap, hGap, vGap);
    }

    public void openGameWindow() {
        JFrame frame = new JFrame("UntitledRPG™");
        buildGameWindow(frame);
    }

    private void buildGameWindow(JFrame frame) {

        /* GameWindow consists of:
        - gameArea, a JTextField where the output from the game will be printed.
        - sidebarTextArea, TODO: Sidebar with info about available commands
        - inputArea, a JPanel where the player enters commands and views them in a log
         */

        // Main build
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Set areas
        gameArea = new JTextArea(5, 10);
        sidebarTextArea = new JTextArea(5, 10);
        JPanel inputArea = new JPanel(new GridBagLayout());

        // GAME AREA
        gameArea.setEditable(false);
        gameArea.setCaretPosition(gameArea.getDocument().getLength());
        gameArea.setWrapStyleWord(true);
        gameArea.setLineWrap(true);
        JScrollPane gameAreaScrollPane = new JScrollPane(gameArea);

        //SIDEBAR AREA
        sidebarTextArea.setEditable(false);

        // INPUT AREA.
        // Set textArea and add it to scrollpane, which is then added to the layout
        inputAreaTextArea = new JTextArea(10,20);
        inputAreaTextArea.setEditable(false);
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());

        // Message in the inputAreaTextArea to user.
        String welcomeMsg= "This is your command log. Your commands will be printed here.";
        printCommandToLog(welcomeMsg);
        JScrollPane textAreaScrollPane = new JScrollPane(inputAreaTextArea);

        // (addComp) method for placing elements in gridBagLayout.
        addComp(inputArea, textAreaScrollPane, 0, 1, 1, 1, GridBagConstraints.BOTH, 2, 2);

        // Label with user instruction on using the inputAreaTextField
        JLabel inputAreaLabel = new JLabel("Enter commands below.");
        addComp(inputArea, inputAreaLabel, 0,2, 1, 1, GridBagConstraints.BOTH, 0.2,0.2);

        // Set jTextField and add it to layout
        inputAreaTextField = new JTextField();
        inputAreaTextField.setText("Start your adventure!"); // Placeholder, see method below
        addComp(inputArea, inputAreaTextField, 0, 3, 2, 2, GridBagConstraints.BOTH, 0.2, 0.2);

        // Listener for sending of a command
        // TODO Listener in separate 'controller' class
        inputAreaTextField.addActionListener((e -> {
            String input = inputAreaTextField.getText();
            if(input.length() > 0) {
                // Print in the inputAreaTextField (log)
                printCommandToLog(input);
                // Send to playerInput.
                playerInput = new PlayerInput();
                playerInput.receiveCommand(input);
                inputAreaTextField.setText("");
                blankCounter = 0;
            }
            else if (blankCounter < 1) {
                printAIToLog("You should make a choice.");
                blankCounter = blankCounter +1;
            }
            else if (blankCounter == 1) {
                printAIToLog("Choice ut up my dude.");
                blankCounter = blankCounter +1;
            }
            else if (blankCounter == 2) {
                printAIToLog("Come on, do something.");
                blankCounter = blankCounter +1;
            }
            else if (blankCounter == 3) {
                printAIToLog(":(");
                blankCounter = blankCounter +1;
            }
            else if (blankCounter == 4) {
                printAIToLog(">:(");
                blankCounter = blankCounter +1;
            }
            else if (input.length() < 1 && blankCounter == 5) {
                popUp pop = new popUp("You're an idiot!", "Seriously");
                blankCounter = blankCounter +1;
            }
            else if (input.length() < 1 && blankCounter > 5) {
                printAIToLog("...");
                blankCounter = blankCounter +1;
            }
        }));

        // Method for the placeholder text.
        inputAreaTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });

        // ASSEMBLY
        // Set vertical splitpane.
        JSplitPane vertSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gameAreaScrollPane, sidebarTextArea);
        vertSplitPane.setDividerLocation(500);
        vertSplitPane.setDividerSize(10);
        vertSplitPane.setEnabled(false);

        // Set horizontal split.
        JSplitPane horiSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, vertSplitPane, inputArea);
        vertSplitPane.setOneTouchExpandable(false);
        horiSplitPane.setDividerLocation(400);
        horiSplitPane.setDividerSize(5);
        horiSplitPane.setEnabled(false);

        frame.add(horiSplitPane);
    }

    // Easier implementation of constraints for gridBagLayout
    // Found on Stack Overflow somewhere, but I can't find the link. - kris
    private void addComp(JPanel panel, JComponent comp
                            , int x, int y, int gWidth
                                , int gHeight, int fill
                                    , double weightx, double weighty) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = gWidth;
        c.gridheight = gHeight;
        c.fill = fill;
        c.weightx = weightx;
        c.weighty = weighty;

        panel.add(comp, c);
    }

    // Prints  player command to inputAreaTextField (log)
    public void printCommandToLog(String text) {
        inputAreaTextArea.append(">" + text + "\n");
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());
    }

    public void printAIToLog(String text) {
        inputAreaTextArea.append("-" + text + "\n");
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());
    }

    // Prints to the gameArea.
    public void printToGameArea(String text) {
         gameArea.append(">" + text + "\n");
         gameArea.setCaretPosition(gameArea.getDocument().getLength());
    }

    // TODO Call this in a for loop to print them as a list
    public void printToSidebarArea(String text) {
         sidebarTextArea.append(">" + text + "\n");
    }
}
