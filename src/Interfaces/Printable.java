package Interfaces;

/*
Has edited this:
- Kristoffer
*/

public interface Printable {

    void printCommandToLog(String text);

    void printResponseToLog(String text);

    void printToGameArea(String text);

    void setInputAreaTextField(String text);

    String getInputAreaTextField();

    void clearSideBarArea();

}
