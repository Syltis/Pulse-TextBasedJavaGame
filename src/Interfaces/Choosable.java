package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.Choice;

public interface Choosable {

    Choice getActiveChoice();

    void nextChoice(int id);
}
