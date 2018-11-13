package GUI;

/*
Has edited this:
- Kristoffer
*/

import javax.swing.*;

class popUp {

    popUp(String infoMessage, String titleBar) {

        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
