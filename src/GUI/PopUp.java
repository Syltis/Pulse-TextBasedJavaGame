package GUI;

import javax.swing.*;

/*
Displays a popUp-window
 */

class PopUp {

    PopUp(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}

