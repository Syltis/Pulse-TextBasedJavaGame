package GUI;

/*
Has edited this:
- Kristoffer
*/

import Interfaces.Choosable;
import Interfaces.Printable;
import Models.Choice;
import Models.MovementCommand;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GameWindow implements Printable {

    private final Choosable choosable;
    private final Printable printable;
    Printer printer;
    JTextArea sidebarTextArea;
    private JPanel sideBarPanel;
    JTextArea gameTextArea;
    private JPanel gameAreaPanel;
    JTextArea inputAreaTextArea;
    JTextField inputAreaTextField;
    private GridBagConstraints c;

     public GameWindow(Choosable choosable, Printable printable) {
         this.choosable = choosable;
         this.printable = printable;

         JFrame gameFrame = new JFrame("UntitledRPG™");
         buildGameWindow(gameFrame);
    }

    private void buildGameWindow(JFrame frame) {
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        int hGap = 5;
        int vGap = 5;
        c.insets = new Insets(hGap, vGap, hGap, vGap);
        /*
        GameWindow consists of:
        - gameTextArea, a JTextAreawhere the output from the game will be printed.
        - sidebarTextArea,a JTextArea info about available commands
        - inputArea, a JPanel where the player enters commands and views them in a log
         */

        // Main build
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(850, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);

        // GAME AREA
        gameTextArea = new JTextArea(5, 10);
        gameTextArea.setEditable(false);
        gameTextArea.setCaretPosition(gameTextArea.getDocument().getLength());
        gameTextArea.setLineWrap(true);
        gameTextArea.setWrapStyleWord(true);

        gameAreaPanel = new JPanel();
        gameAreaPanel.setLayout(new BorderLayout());
        gameAreaPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        gameAreaPanel.add(gameTextArea);

        JScrollPane gameAreaScrollPane = new JScrollPane(gameTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        gameAreaPanel.add(gameAreaScrollPane);

        // SIDEBAR AREA
        sidebarTextArea = new JTextArea(5, 10);
        sidebarTextArea.setEditable(false);
        sidebarTextArea.setWrapStyleWord(true);
        sidebarTextArea.setLineWrap(true);
        sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BorderLayout());
        sideBarPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        sideBarPanel.add(sidebarTextArea);


        // INPUT AREA.
        // Set textArea and add it to scrollpane, which is then added to the layout
        JPanel inputArea = new JPanel(new GridBagLayout());
        inputAreaTextArea = new JTextArea(10,20);
        inputAreaTextArea.setEditable(false);
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());

        printCommandToLog("This is your command log. Your commands will be logged here.");
        JScrollPane textAreaScrollPane = new JScrollPane(inputAreaTextArea);

        // (addComp) method for placing elements in gridBagLayout.
        addComp(inputArea, textAreaScrollPane, 0, 1, 1, 1, GridBagConstraints.BOTH, 2, 2);

        JButton emptyLogButton = new JButton("Empty Log");
        JButton menuButton = new JButton("Main Menu");
        JPanel inputAreaButtonPanel = new JPanel();

        inputAreaButtonPanel.setLayout(new BoxLayout(inputAreaButtonPanel, BoxLayout.Y_AXIS));
        inputAreaButtonPanel.add(emptyLogButton);
        inputAreaButtonPanel.add(Box.createRigidArea(new Dimension(0,8)));
        inputAreaButtonPanel.add(menuButton);

        addComp(inputArea, inputAreaButtonPanel, 1, 0, 2, 2, GridBagConstraints.BOTH, 0.2, 0.2);


        // Label with user instruction on using the inputAreaTextField
        JLabel inputAreaLabel = new JLabel("Enter commands below.");
        addComp(inputArea, inputAreaLabel, 0,2, 1, 1, GridBagConstraints.BOTH, 0.2,0.2);


        // Set jTextField and add it to layout
        inputAreaTextField = new JTextField();
        inputAreaTextField.setText("Start your adventure!"); // Placeholder, see method below

        addComp(inputArea, inputAreaTextField, 0, 3, 2, 2, GridBagConstraints.BOTH, 0.2, 0.2);
        // Listener for sending of a command
        inputAreaTextField.addActionListener(new CommandListener(GameWindow.this, choosable) {});

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
        JSplitPane vertSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gameAreaPanel, sideBarPanel);
        vertSplitPane.setDividerLocation(680);
        vertSplitPane.setDividerSize(5);
        vertSplitPane.setOneTouchExpandable(false);
        vertSplitPane.setResizeWeight(1.0);
        vertSplitPane.setEnabled(false);

        // Set horizontal split.
        JSplitPane horiSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, vertSplitPane, inputArea);
        horiSplitPane.setDividerLocation(400);
        horiSplitPane.setDividerSize(5);
        vertSplitPane.setOneTouchExpandable(false);
        vertSplitPane.setResizeWeight(1.0);
        horiSplitPane.setEnabled(false);

        frame.add(horiSplitPane);
    }

    // Easier implementation of constraints for gridBagLayout
    // Found on Stack Overflow somewhere.
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

        inputAreaTextArea.append("> " + text + "\n");
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());
    }

    public void printResponseToLog(String text) {

        inputAreaTextArea.append("- " + text + "\n");
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());
    }

    // Prints to the gameTextArea.
    public void printResponseToGameArea(String title, String descrption) {
         gameTextArea.append(" " + title + "\n");
         gameTextArea.append("> " + descrption + "\n");
         gameTextArea.setCaretPosition(gameTextArea.getDocument().getLength());
    }

    public void printCommandToGameArea(String text) {
         gameTextArea.append("- " + text + "\n" + "\n");
         gameTextArea.setCaretPosition(gameTextArea.getDocument().getLength());
    }

    // Has alternative for dash ('-') in front of printed string
    public void printToSidebarArea(String text,String dash) {
        if (dash.equals("dash")) {
            sidebarTextArea.append("- " + text + "\n");
            sidebarTextArea.setCaretPosition(sidebarTextArea.getDocument().getLength());
        }
        else {
            sidebarTextArea.append(text + "\n");
            sidebarTextArea.setCaretPosition(sidebarTextArea.getDocument().getLength());
        }
    }

    public void feedSideBar(Choice activeChoice) {
        if (activeChoice.getAvailableMovementCommands() != null && activeChoice.getAvailableMovementCommands().length > 0) {
            printToSidebarArea("MOVEMENT:", "dash");
            for (MovementCommand aMovementCommand : activeChoice.getAvailableMovementCommands()) {
                printToSidebarArea(aMovementCommand.getMovementCommand(), "dash");
            }
        }
        if (activeChoice.getAvailableActionCommands() != null && !activeChoice.getAvailableActionCommands().isEmpty()){
            printToSidebarArea("", "nodash");
            printToSidebarArea("ACTIONS:", "dash");
            for (String aCommand:activeChoice.getAvailableActionCommands()) {
                printToSidebarArea(aCommand.replaceAll("[-!@#$%^&*().?\":{}|<>0-9+/'=\\[\\]]+",""), "dash");
            }
        }
        if (activeChoice.getAvailableCombatCommands() != null && !activeChoice.getAvailableCombatCommands().isEmpty()) {
            printToSidebarArea("", "nodash");
            printToSidebarArea("COMBAT:", "dash");
            for (String aCombatCommand:activeChoice.getAvailableCombatCommands()) {
                printToSidebarArea(aCombatCommand.replaceAll("[-!@#$%^&*().?\":{}|<>0-9+/'=\\[\\]]+", ""),"dash");
            }
        }
    }

    public void clearSideBarArea() {
         sidebarTextArea.setText("");
    }

    public String getInputAreaTextField() {
         return inputAreaTextField.getText();
    }

    public void setInputAreaTextField(String text) {
         inputAreaTextField.setText(text);
    }
}
