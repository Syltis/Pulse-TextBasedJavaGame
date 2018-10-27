package GUI;

import javax.swing.*;

/*
Has edited this:
- Kristoffer
*/

public class popUp {

    public popUp() {

    }
    public popUp(String infoMessage, String titleBar) {

        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
