package GUI;

/*
- Class that instantiates a frame with two buttons, asking the player if they really wants to exit the game.
- The class takes the IGameWindow interface to allow it to dispose of the GameWindow through the disposeWindow-method
 */

import Interfaces.IGameWindowDispose;
import javax.swing.*;
import java.awt.*;

class ExitConfirm {

	private final JFrame frame;
	private final String exitGame = "Exit Game";
	private final String continueGame = "Continue Game";
	private final IGameWindowDispose IGameWindowDispose;

	ExitConfirm(IGameWindowDispose IGameWindowDispose) {
		this.IGameWindowDispose = IGameWindowDispose;
		frame = new JFrame();
		buildWindow(frame);
	}

	private void buildWindow(JFrame frame) {

		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setSize(250, 180);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new GridLayout(1,1));

		JPanel container = new JPanel();
		container.setLayout(new GridLayout(3,1));

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		JLabel label1 = new JLabel("Are you sure you want to exit the game?");
		JButton exitButton = selectButton(exitGame);
		JButton continueButton = selectButton(continueGame);

		panel1.add(label1);
		panel2.add(continueButton);
		panel3.add(exitButton);

		container.add(panel1);
		container.add(panel2);
		container.add(panel3);

		frame.add(container);
		frame.setVisible(true);
	}

	private JButton selectButton(String btnName) {
		JButton returned = new JButton(btnName);
		returned.addActionListener(e -> {
			JButton source = (JButton)e.getSource();
			if (source.getText().equalsIgnoreCase(exitGame)) {
				IGameWindowDispose.disposeWindow();
				frame.dispose();
			}
			if (source.getText().equalsIgnoreCase(continueGame)) {
				frame.dispose();
			}
		});
		return returned;
	}
}
