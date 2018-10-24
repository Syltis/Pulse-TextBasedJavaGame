package GUI;

import javax.swing.*;

public class popUp {

    public void popUpWindow(String infoMessage, String titleBar) {

        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
