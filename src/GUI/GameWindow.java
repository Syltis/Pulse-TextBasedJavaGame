package GUI;

import javafx.scene.control.SplitPane;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    private JTextArea inputArea;
    private JTextArea gameArea;
    private JTextArea sideBarArea;

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
        inputArea = new JTextArea(5, 10);

        // Set vertical splitpane. Second layer
        JSplitPane vertSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, gameArea, sideBarArea);

        vertSplitPane.setDividerLocation(400);
        vertSplitPane.setDividerSize(10);
        vertSplitPane.setEnabled(false);

        // Set horizontal split. Top layer
        JSplitPane horiSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, vertSplitPane, inputArea);
        vertSplitPane.setOneTouchExpandable(false);
        horiSplitPane.setDividerLocation(500);
        horiSplitPane.setDividerSize(10);
        horiSplitPane.setEnabled(false);


        frame.add(horiSplitPane);

        return frame;
    }
}
