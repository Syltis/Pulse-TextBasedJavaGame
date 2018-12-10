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
    private final String imagePath = "images/spaceShip_8Bit_Cropped.png";
    private GridBagConstraints c;

    public MainMenu()
    {
        buildWindow(frame);
    }

    private void buildWindow(JFrame frame) {

        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        int hGap = 5;
        int vGap = 5;
        c.insets = new Insets(hGap, vGap, hGap, vGap);

        // Main build
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(450, 360);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Panel for top image
        JPanel imagePanel = new JPanel();

        // Generate imageLabel with path specified at the top
        imagePanel.add(generateImageLabel(imagePath));

        // Panel for title
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("<html><span style='font-size:16px'>" + "Welcome to the SpaceRPG!" + "</span></html>");
        titlePanel.add(titleLabel);

        // Panels for individual buttons
        JPanel newGameButtonPanel = new JPanel();
        JPanel loadGameButtonPanel = new JPanel();
        JPanel exitGameButtonPanel = new JPanel();
        JPanel settingsButtonPanel = new JPanel(new GridLayout(1,2));

        // Panel for button-panels
        JPanel buttonContainer = new JPanel(new GridLayout(2, 3));
        JPanel buttonPanelTop = new JPanel(new GridLayout(1,3));
        JPanel buttonPanelBottom = new JPanel(new GridLayout(1,2));

        // Initialize buttons
        newGameButtonPanel.add(selectGameTypeBtn(newGame));
        loadGameButtonPanel.add(selectGameTypeBtn(loadGame));
        exitGameButtonPanel.add(selectGameTypeBtn(exitGame));
        settingsButtonPanel.add(new JPanel());
        settingsButtonPanel.add(selectGameTypeBtn(settings));

        buttonPanelTop.add(newGameButtonPanel);
        buttonPanelTop.add(loadGameButtonPanel);
        buttonPanelTop.add(exitGameButtonPanel);
        buttonContainer.add(buttonPanelTop);

        buttonPanelBottom.add(new JLabel("<html>" +"Written as a CSC305 Project at the University of Rhode Island, Fall 2018." + "</html>"));
        buttonPanelBottom.add(settingsButtonPanel);

        // Container for field1, 2, 3
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());

        // Add panels to grid
        addComp(container, imagePanel, 0, 0, 1,1, GridBagConstraints.BOTH, 1, 1);
        addComp(container, titlePanel, 0, 1,1, 1, GridBagConstraints.BOTH, 1, 1);
        addComp(container, buttonContainer, 0, 2, 1, 1, GridBagConstraints.BOTH, 1, 1);
        addComp(container, buttonPanelBottom, 0, 3, 1, 1, GridBagConstraints.BOTH, 1, 1);

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

    // Easier implementation of constraints for gridBagLayout
    // Found on Stack Overflow somewhere.
    private void addComp(JPanel panel, JComponent comp
            , int column, int row, int gWidth
            , int gHeight, int fill
            , double weightx, double weighty) {
        c.gridx = column;
        c.gridy = row;
        c.gridwidth = gWidth;
        c.gridheight = gHeight;
        c.fill = fill;
        c.weightx = weightx;
        c.weighty = weighty;

        panel.add(comp, c);
    }

    private JLabel generateImageLabel(String imagePath) {
        JLabel imageLabel = null;
        try {
            File imageFile = new File(imagePath);
            BufferedImage myImage = ImageIO.read(imageFile);
            imageLabel = new JLabel(new ImageIcon(myImage.getScaledInstance(430, 150,Image.SCALE_FAST)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return imageLabel;
    }
}
