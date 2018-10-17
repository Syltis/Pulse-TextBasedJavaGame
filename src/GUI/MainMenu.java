package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MainMenu extends JFrame {

    private String gameDesc;
    private String newGame;
    private String loadGame;
    private String settings;
    private JFrame frame = new JFrame("UntitledRPG™ Main Menu");

    public MainMenu() {
        newGame = "New Game";
        loadGame = "Load Game";
        settings = "Settings";
        buildWindow(frame);
    }
    private JFrame buildWindow(JFrame frame)
    {
        // Main build
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 220);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Draw layout with grid
        // Look into GridBagLayout if this proves insufficient
        frame.setLayout(new GridLayout(3,1));
        JPanel field1 = new JPanel();
        JPanel field2 = new JPanel(new GridLayout(1, 3));
        JPanel field21 = new JPanel();
        JPanel field22 = new JPanel();
        JPanel field23 = new JPanel();
        field2.add(field21);
        field2.add(field22);
        field2.add(field23);
        JPanel field3 = new JPanel();

        // Instantiate objects
        JLabel welcomeTextLabel = new JLabel();
        JLabel gameDescLabel = new JLabel();
        JButton newGameBtn = selectGameTypeBtn(newGame);
        JButton loadGameBtn = selectGameTypeBtn(loadGame);
        JButton settingsBtn = selectGameTypeBtn(settings);

        // Set values. Can be integrated to the instantiation for cleaner look
        String welcomeText = "Welcome to the Untitled RPG!";
        gameDesc = "<html>Press 'New Game' to start your adventure,<br /> or 'Load Game' to continue an existing one!</html> ";
        settingsBtn.setText("Settings");
        welcomeTextLabel.setText(welcomeText);
        gameDescLabel.setText(gameDesc);

        //Add objects to panels
        field1.add(welcomeTextLabel);
        field21.add(newGameBtn);
        field22.add(loadGameBtn);
        field23.add(settingsBtn);
        field3.add(gameDescLabel);

        // Add panels to grid
        frame.add(field1);
        frame.add(field2);
        frame.add(field3);

        frame.setVisible(true);
        return frame;
    }

    private JButton selectGameTypeBtn(String btnName) {
        JButton returned = new JButton(btnName);
        returned.addActionListener(e -> {
            JButton source = (JButton)e.getSource();
            if (source.getText().equalsIgnoreCase(newGame)) {
                new GameWindow();
                frame.dispose();
            }
            if (source.getText().equalsIgnoreCase(loadGame)) {
                MainMenu.popUpWindow("You have no saves to load!", "Error!");
            }
            if (source.getText().equalsIgnoreCase(settings)) {
                MainMenu.popUpWindow("We haven'made any settings yet!", "Error!");
            }
        });
        return returned;
    }

    private static void popUpWindow(String infoMessage, String titleBar) {

        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
