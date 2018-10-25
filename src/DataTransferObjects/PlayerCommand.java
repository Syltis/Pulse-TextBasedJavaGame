package DataTransferObjects;

import java.util.ArrayList;

public class PlayerCommand {

    private String actionCommand;

    private String commandTarget;

    private ArrayList<String> command;

    public PlayerCommand() {

    }

    public PlayerCommand(String actionCommand, String commandTarget) {
        this.actionCommand = actionCommand;
        this.commandTarget = commandTarget;
    }

    public String getActionCommand() {
        return actionCommand;
    }

    public void setActionCommand(String actionCommand) {
        this.actionCommand = actionCommand;
    }

    public String getCommandTarget() {
        return commandTarget;
    }

    public void setCommandTarget(String commandTarget) {
        this.commandTarget = commandTarget;
    }

    public ArrayList<String> getCommandList() { return command; }

    public void setCommandList(ArrayList<String> commandList) { this.command = commandList;}
}
