package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.Choice;
import Models.PlayerCommand;

public interface Choosable {
    public Choice getActiveChoice();

    public void setActiveChoice(Choice choice);

    public PlayerCommand getActivePlayerCommand();

    public void setActivePlayerCommand(PlayerCommand command);

    public void nextChoice(int id);

    public Choice getChoiceFromParser(int id);
}
