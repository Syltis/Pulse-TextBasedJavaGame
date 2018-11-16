package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.ChoiceV2;

public interface Choosable {

    ChoiceV2 getActiveChoice();

    void nextChoice(String id);
}
