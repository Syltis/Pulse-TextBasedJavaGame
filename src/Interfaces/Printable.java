package Interfaces;

public interface Printable {

    void printCommandToLog(String text);

    void printResponseToLog(String text);

    void printToGameArea(String text);

    String getInputAreaTextField();

    void setInputAreaTextField(String text);
}
