package Interfaces;

import Models.Item;

import java.util.List;

// Interface for working with the different text-areas/field in the GameWindow
// TODO Split this into smaller interfaces - S.O.L.(I).D - Interface segregation principle

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
