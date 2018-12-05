package GUI;

/*
Has edited this:
- Kristoffer
*/

import Gameplay.GameSettings;
import Interfaces.Choosable;
import Interfaces.Playable;
import Interfaces.Printable;
import Models.ActionCommand;
import Models.Item;
import Models.MovementCommand;
import Models.Scenario;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class GameWindow implements Printable {

    private final Choosable choosable;
    private final Playable playable;
    GameSettings gameSettings = GameSettings.getInstance();
    JTextArea sidebarTextArea;
    private JPanel sideBarPanel;
    JTextArea gameTextArea;
    private JPanel gameAreaPanel;
    JTextArea inputAreaTextArea;
    JTextField inputAreaTextField;
    private GridBagConstraints c;

    private String mainMenuButtonText = "Main menu";
    private String emptyLogButtonText = "Empty Log";
    private String sendButtonText = "Send";

     public GameWindow(Choosable choosable, Playable playable)
     {
         this.choosable = choosable;
         this.playable = playable;

         JFrame gameFrame = new JFrame("UntitledRPG™");
         buildGameWindow(gameFrame);
    }

    private void buildGameWindow(JFrame frame) {
         /*
        GameWindow consists of:
        - gameTextArea, a JTextAreawhere the output from the game will be printed.
        - sideBarPanel,a JTextArea info about available commands
        - inputArea, a JPanel where the player enters commands and views them in a log
         */

        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        int hGap = 5;
        int vGap = 5;
        c.insets = new Insets(hGap, vGap, hGap, vGap);

        // Main build
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
        JScrollPane sideBarAreaScrollPane = new JScrollPane(sidebarTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        sideBarPanel.add(sideBarAreaScrollPane);

// INPUT AREA.
        // Set textArea and add it to scrollpane, which is then added to the layout
        JPanel inputAreaPanel = new JPanel(new GridBagLayout());
        inputAreaTextArea = new JTextArea(10,20);
        inputAreaTextArea.setEditable(false);
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());

        JScrollPane textAreaScrollPane = new JScrollPane(inputAreaTextArea);
        // (addComp) method for placing elements in gridBagLayout.
        addComp(inputAreaPanel, textAreaScrollPane, 0, 1, 1, 1, GridBagConstraints.BOTH, 2, 2);
        JButton mainMenuButton = gameWindowButton(mainMenuButtonText);
        JButton emptyLogButton = gameWindowButton(emptyLogButtonText);

        JPanel inputAreaButtonPanel = new JPanel();
        inputAreaButtonPanel.setLayout(new BoxLayout(inputAreaButtonPanel, BoxLayout.Y_AXIS));
        inputAreaButtonPanel.add(mainMenuButton);
        inputAreaButtonPanel.add(Box.createRigidArea(new Dimension(0,79)));
        inputAreaButtonPanel.add(emptyLogButton);
        addComp(inputAreaPanel, inputAreaButtonPanel, 1, 0, 2, 2, GridBagConstraints.BOTH, 0.2, 0.2);

        // Label with user instruction on using the inputAreaTextField
        JLabel inputAreaLabel = new JLabel("Enter commands below.");
        addComp(inputAreaPanel, inputAreaLabel, 0,2, 1, 1, GridBagConstraints.BOTH, 0.2,0.2);

        // Set jTextField and add it to layout
        inputAreaTextField = new JTextField("Start your adventure!",100);
        addComp(inputAreaPanel, inputAreaTextField, 0, 3, 2, 2, GridBagConstraints.BOTH, 0.2, 0.2);

        JButton sendCommandButton = gameWindowButton(sendButtonText);
        addComp(inputAreaPanel, sendCommandButton, 1, 3, 2, 2, GridBagConstraints.BOTH, 0.2, 0.2);

// LISTENERS
        // Listener for sending of a command
        inputAreaTextField.addActionListener(new CommandListener(GameWindow.this, choosable, playable));

        sendCommandButton.addActionListener(new CommandListener(GameWindow.this, choosable, playable));

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
        vertSplitPane.setDividerLocation(670);
        vertSplitPane.setDividerSize(5);
        vertSplitPane.setOneTouchExpandable(false);
        vertSplitPane.setResizeWeight(1.0);
        vertSplitPane.setEnabled(false);

        // Set horizontal split.
        JSplitPane horiSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, vertSplitPane, inputAreaPanel);
        horiSplitPane.setDividerLocation(400);
        horiSplitPane.setDividerSize(5);
        horiSplitPane.setOneTouchExpandable(false);
        horiSplitPane.setResizeWeight(1.0);
        horiSplitPane.setEnabled(false);

        printResponseToLog("This is your command log. Your commands will be logged here.");
        frame.add(horiSplitPane);
    }

// MUTATORS AND HELPERS
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

    // Returns a button and a set actionListener. Cant be used with the 'Send'-button, as it uses the CommandListener
    public JButton gameWindowButton(String buttonName) {
         JButton returnedButton = new JButton(buttonName);
         returnedButton.addActionListener(e -> {
             JButton source = (JButton)e.getSource();
             if (source.getText().equalsIgnoreCase(mainMenuButtonText)) {
                 new MainMenu();
             }
             if (source.getText().equalsIgnoreCase(emptyLogButtonText)) {
                 emptyLog();
             }
         });
         return returnedButton;
    }

    // Prints  player command to inputAreaTextField (log)
    public void printCommandToLog(String text) {

        inputAreaTextArea.append(" " + gameSettings.getTurnCount() + ". " + text + "\n");
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());
    }

    public void printResponseToLog(String text) {

        inputAreaTextArea.append("- " + text + "\n");
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());
    }

    public void emptyLog(){
         inputAreaTextArea.setText("");
    }

    // Prints to the gameTextArea.
    public void printScenarioToGameArea(String title, String descrption) {
         gameTextArea.append(" " + gameSettings.getTurnCount() + ". " + title + "\n");
         gameTextArea.append("> " + descrption + "\n");
         gameTextArea.setCaretPosition(gameTextArea.getDocument().getLength());
    }

    public void printCommandToGameArea(String text) {
         gameTextArea.append("- " + text + "\n" + "\n");
         gameTextArea.setCaretPosition(gameTextArea.getDocument().getLength());
    }

    public void printInventoryToGameArea(List<Item> inventory) {
         if (inventory.isEmpty()) {
             gameTextArea.append("> Inventory empty" + "\n");
         }
         else {
             gameTextArea.append("\n" + "> Inventory:" + "\n");
             for (Item aItem:inventory) {
                 gameTextArea.append("- " + aItem.getItemName() + " (" + aItem.getItemType() + ")" + "\n");
             }
         }
    }

    // Has alternative for dash ('-') in front of printed string
    public void printToSidebarArea(String text,String hasDash) {
        if (hasDash.equals("dash")) {
            sidebarTextArea.append("- " + text + "\n");
            sidebarTextArea.setCaretPosition(sidebarTextArea.getDocument().getLength());
        }
        else {
            sidebarTextArea.append(text + "\n");
            sidebarTextArea.setCaretPosition(sidebarTextArea.getDocument().getLength());
        }
    }

    public void printToSidebarArea(int numb, String hasDash) {
        if (hasDash.equals("dash")) {
            sidebarTextArea.append("- " + numb + "\n");
            sidebarTextArea.setCaretPosition(sidebarTextArea.getDocument().getLength());
        }
        else {
            sidebarTextArea.append(numb + "\n");
            sidebarTextArea.setCaretPosition(sidebarTextArea.getDocument().getLength());
        }
    }

    // Prints the available commands to the sidebar
    public void feedSideBar(Scenario activeScenario) {
         if (activeScenario.getAvailableMovementCommands() != null && activeScenario.getAvailableMovementCommands().length > 0) {
             printToSidebarArea("MOVEMENT:", "dash");
             for (MovementCommand aMovementCommand : activeScenario.getAvailableMovementCommands()) {
                printToSidebarArea(aMovementCommand.getMovementCommand(), "dash");
            }
         }
         if (activeScenario.getAvailableActionCommands() != null && activeScenario.getAvailableActionCommands().length > 0){
            printToSidebarArea("", "nodash");
            printToSidebarArea("ACTIONS:", "dash");
            for (ActionCommand aActionCommand: activeScenario.getAvailableActionCommands()) {
                printToSidebarArea(aActionCommand.getActionCommand(), "dash");
            }
        }
        if (activeScenario.getAvailableCombatCommands() != null && !activeScenario.getAvailableCombatCommands().isEmpty()) {
            printToSidebarArea("", "nodash");
            printToSidebarArea("COMBAT:", "dash");
            for (String aCombatCommand: activeScenario.getAvailableCombatCommands()) {
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
