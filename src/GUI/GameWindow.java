package GUI;

import Managers.PlayerInput;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GameWindow {

    private JTextArea sideBarArea;
    private JTextArea gameArea; // Am I sure about this? - kris
    private JTextArea inputAreaTextArea;
    private JTextField inputAreaTextField;
    private GridBagConstraints c;
    private PlayerInput playerInput;

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
        - sideBarArea, TODO: Sidebar with info about available commands
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
        sideBarArea = new JTextArea(5, 10);
        JPanel inputArea = new JPanel(new GridBagLayout());

        // GAME AREA
        gameArea.setEditable(false);
        gameArea.setCaretPosition(gameArea.getDocument().getLength());
        gameArea.setWrapStyleWord(true);
        gameArea.setLineWrap(true);
        JScrollPane gameAreaScrollPane = new JScrollPane(gameArea);

        //SIDEBAR AREA
        sideBarArea.setEditable(false);

        // INPUT AREA.
        // Set textArea and add it to scrollpane, which is then added to the layout
        inputAreaTextArea = new JTextArea(10,20);
        inputAreaTextArea.setEditable(false);
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());

        // Message in the inputAreaTextArea to user.
        String welcomeMsg= "This is your command log. Your commands will be printed here.";
        printToLog(welcomeMsg);
        JScrollPane textAreaScrollPane = new JScrollPane(inputAreaTextArea);

        // (addComp) method for placing elements in gridBagLayout.
        addComp(inputArea, textAreaScrollPane, 0, 1, 1, 1, GridBagConstraints.BOTH, 2, 2);

        // Label with user instruction on using the inputAreaTextField
        JLabel inputAreaLabel = new JLabel("Enter commands below.");
        addComp(inputArea, inputAreaLabel, 0,2, 1, 1, GridBagConstraints.BOTH, 0.2,0.2);

        // Set jTextfield and add it to layout
        inputAreaTextField = new JTextField();
        inputAreaTextField.setText("Start your adventure!"); // Placeholder, see method below
        addComp(inputArea, inputAreaTextField, 0, 3, 2, 2, GridBagConstraints.BOTH, 0.2, 0.2);

        // Listener for sending of a command
        inputAreaTextField.addActionListener((e -> {
            String command = inputAreaTextField.getText();
            if(command.length() > 0) {
                playerInput = new PlayerInput();
                // Print in the inputAreaTextField (log)
                printToLog(command);
                // Send to playerInput.
                playerInput.receiveCommand(command);
                inputAreaTextField.setText("");
            }
            else {
                printToLog("You should make a choice.");
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
        // Set vertical splitpane. Second layer
        JSplitPane vertSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gameAreaScrollPane, sideBarArea);
        vertSplitPane.setDividerLocation(500);
        vertSplitPane.setDividerSize(10);
        vertSplitPane.setEnabled(false);

        // Set horizontal split. Top layer
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
    public void printToLog(String text) {
        inputAreaTextArea.append(">" + text + "\n");
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());
    }

    // Prints strings to the gameArea.
    public void printToGameArea(String text) {
         gameArea.append(">" + text + "\n");
         gameArea.setCaretPosition(gameArea.getDocument().getLength());
    }
}
