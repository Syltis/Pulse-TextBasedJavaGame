package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.Item;

import java.util.List;

public interface IGameWindowPrint {

    void printCommandToLog(String text);

    void printResponseToLog(String text);

    void printToGameArea(String text, boolean hasDash);

    void printScenarioToGameArea(String title, String description);

    void printInventoryToGameArea(List<Item> inventory);

    void setInputAreaTextField(String text);

    String getInputAreaTextField();

    void clearSideBarArea();

}
