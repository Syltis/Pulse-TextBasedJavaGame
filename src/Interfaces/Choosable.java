package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.ChoiceV2;
import Models.PlayerCommand;

public interface Choosable {
    public ChoiceV2 getActiveChoice();

    public void setActiveChoice(ChoiceV2 choice);

    public PlayerCommand getActivePlayerCommand();

    public void setActivePlayerCommand(PlayerCommand command);
}
