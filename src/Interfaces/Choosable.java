package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.Choice;
import Models.PlayerCommand;

public interface Choosable {
    Choice getActiveChoice();

    void setActiveChoice(Choice choice);

    PlayerCommand getActivePlayerCommand();

    void setActivePlayerCommand(PlayerCommand command);

    void nextChoice(int id);
}
