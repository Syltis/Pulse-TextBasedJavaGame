package Interfaces;

import Models.ChoiceV2;

public interface Choosable {
    public ChoiceV2 getActiveChoice();

    public void setActiveChoice(ChoiceV2 choice);
}
