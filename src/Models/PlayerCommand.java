package Models;

/*
Has edited this:
- Kristoffer
*/

public class PlayerCommand {

    private String actionCommand;
    private String commandTarget;

    public PlayerCommand() {

    }

    public PlayerCommand(String actionCommand, String commandTarget) {
        this.actionCommand = actionCommand;
        this.commandTarget = commandTarget;
    }

    public String getActionCommand() {
        return actionCommand;
    }

    public void setActionCommand(String actionCommand) { this.actionCommand = actionCommand; }

    public String getCommandTarget() {
        return commandTarget;
    }

    public void setCommandTarget(String commandTarget) {
        this.commandTarget = commandTarget;
    }

}