package GUI;

import javafx.scene.control.SplitPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GameWindow {

    private JPanel inputArea;
    private JTextArea inputAreaTextArea;
    private JLabel inputAreaLabel;
    private JTextField inputAreaTextField;
    private JTextArea gameArea;
    private JTextArea sideBarArea;
    private GridBagConstraints c;
    private final int hGap = 5;
    private final int vGap = 5;

    public GameWindow() {

        c = new GridBagConstraints ();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets( hGap, vGap, hGap, vGap );
        JFrame frame = new JFrame("UntitledRPG™");
        buildGameWindow(frame);
    }


    private JFrame buildGameWindow(JFrame frame) {

        // Main build
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Set areas
        gameArea = new JTextArea(5, 10);
        gameArea.setEditable(false);
        sideBarArea = new JTextArea(5, 10);
        sideBarArea.setEditable(false);

        // Set inputArea.
        // Contains an textArea for displaying recent player input,
        // a JLabel for a short text above the textField below for player input
        inputArea = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Set textArea and add it to scrollpane, which is then added to the layout
        inputAreaTextArea = new JTextArea(10,20);
        inputAreaTextArea.setEditable(false);
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());
        JScrollPane textAreaScrollPane = new JScrollPane(inputAreaTextArea);
        addComp(inputArea, textAreaScrollPane, 0, 1, 1, 1, GridBagConstraints.BOTH, 2, 2);

        inputAreaLabel = new JLabel("Enter commands below.");
        addComp(inputArea, inputAreaLabel, 0,2, 1, 1, GridBagConstraints.BOTH, 0.2,0.2);

        // Set jTextfield and add it to layout
        inputAreaTextField = new JTextField();
        inputAreaTextField.setText("Start your adventure!"); // Placeholder, see method below
        addComp(inputArea, inputAreaTextField, 0, 3, 2, 2, GridBagConstraints.BOTH, 0.2, 0.2);

        // Listener for enter-click
        inputAreaTextField.addActionListener((e -> {
            if(inputAreaTextField.getText().length() > 0) {
                sendPlayerInput(inputAreaTextField.getText());
                printToLog(inputAreaTextField.getText());
                inputAreaTextField.setText("");
            }
        }));

        // Method for the placeholder text. Show up one time before textField is in focus
        inputAreaTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });

        // Set vertical splitpane. Second layer
        JSplitPane vertSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gameArea, sideBarArea);
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

        return frame;
    }

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

    public void sendPlayerInput(String playerInput) {
        // TODO
    }

    // Prints  player command to inputAreaTextField (log)
    public void printToLog(String message) {
        inputAreaTextArea.append(">" + message + "\n");
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());
    }
}
