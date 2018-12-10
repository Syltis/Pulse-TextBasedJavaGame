package GUI;

/*
Has edited this:
- Kristoffer
*/

import Gameplay.NewGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu {

    private final String newGame = "New Game";
    private final String loadGame = "Load Game";
    private final String exitGame = "Exit Game";
    private final String settings = "Settings";
    private final JFrame frame = new JFrame("UntitledRPG™ Main Menu");
    private GridBagConstraints c;

    public MainMenu()
    {
        buildWindow(frame);
    }

    private void buildWindow(JFrame frame)
    {

        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        int hGap = 5;
        int vGap = 5;
        c.insets = new Insets(hGap, vGap, hGap, vGap);

        // Main build
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Container for field1, 2, 3
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(3,1));

        // Panel for image
        JPanel imagePanel = new JPanel();


        // Panel for button-panels
        JPanel buttonContainer = new JPanel(new GridLayout(2, 3));

        JPanel buttonPanelTop = new JPanel(new GridLayout(1,3));

        // Panel for game-description
        JPanel gameDescPanel = new JPanel();

        // Panels for buttons
        JPanel newGameButtonPanel = new JPanel();
        JPanel loadGameButtonPanel = new JPanel();
        JPanel exitGameButtonPanel = new JPanel();
        JPanel settingsButtonPanel = new JPanel();

        newGameButtonPanel.add(selectGameTypeBtn(newGame));
        loadGameButtonPanel.add(selectGameTypeBtn(loadGame));
        exitGameButtonPanel.add(selectGameTypeBtn(exitGame));
        settingsButtonPanel.add(selectGameTypeBtn(settings));
        buttonPanelTop.add(newGameButtonPanel);
        buttonPanelTop.add(loadGameButtonPanel);
        buttonPanelTop.add(exitGameButtonPanel);

        buttonContainer.add(buttonPanelTop);
        buttonContainer.add(settingsButtonPanel);

        try {
            BufferedImage myImage = ImageIO.read(new File("src/GUI/spaceShip_8Bit_Cropped.png"));
            JLabel imageLabel = new JLabel(new ImageIcon(myImage.getScaledInstance(400, 200,Image.SCALE_FAST)));
            imagePanel.add(imageLabel);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        JLabel gameDescLabel = new JLabel("<html>Press 'New Game' to start your adventure,<br /> or 'Load Game' to continue an existing one!</html> ");
        gameDescPanel.add(gameDescLabel);

        // Add panels to grid
        container.add(imagePanel);
        container.add(buttonContainer);
        container.add(gameDescPanel);

        frame.add(container);
        frame.setVisible(true);
    }

    private JButton selectGameTypeBtn(String btnName) {
        JButton returned = new JButton(btnName);
        returned.addActionListener(e -> {
            JButton source = (JButton)e.getSource();
            if (source.getText().equalsIgnoreCase(newGame)) {
                new NewGame();
                frame.dispose();
            }
            if (source.getText().equalsIgnoreCase(loadGame)) {
                new PopUp("You have no saves to load!", "Error!");

            }
            if (source.getText().equalsIgnoreCase(exitGame)) {
                frame.dispose();
            }
            // TODO: Pass settings as an object to be used by gameWindow. Here we could add game dimensions
            if (source.getText().equalsIgnoreCase(settings)) {
                new PopUp("We haven'made any settings yet!", "Error!");
            }
        });
        return returned;
    }
}
