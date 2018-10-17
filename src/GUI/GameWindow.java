package GUI;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    public GameWindow() {

        JFrame frame = new JFrame("UntitledRPG™");
        buildGameWindow(frame);
    }

    private JFrame buildGameWindow(JFrame frame) {

        // Main build
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        return frame;
    }
}
