package GUI;

import javafx.scene.control.SplitPane;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    private JPanel inputArea;
    private JTextArea inputAreaTextArea;
    private JTextField inputAreaTextField;
    private JTextArea gameArea;
    private JTextArea sideBarArea;

    // for the gridbagLayout
    static boolean shouldFill = true;

    public GameWindow() {

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
        // and a textField for player input
        inputArea = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if(shouldFill) {
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        inputAreaTextArea = new JTextArea();
        inputAreaTextArea.setEditable(false);

        // Set scrollPane
        inputAreaTextArea.setCaretPosition(inputAreaTextArea.getDocument().getLength());
        JScrollPane textAreaScrollPane = new JScrollPane(inputAreaTextArea);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        inputArea.add(textAreaScrollPane,c);

        inputAreaTextField = new JTextField();
        c.gridx = 0;
        c.gridy = 1;
        // Add textArea and textField to inputArea
        inputArea.add(inputAreaTextField, c);

        inputAreaTextField.addActionListener((e -> {
            if(inputAreaTextField.getText().length() > 0) {
                sendPlayerInput(inputAreaTextField.getText());
                inputAreaTextField.setText("");
            }
        }));

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

    public void sendPlayerInput(String playerInput) {

    }
}
