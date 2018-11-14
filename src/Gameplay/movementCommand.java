package Gameplay;

public class movementCommand {

    private String command;
    private String nextChoiceId;

    public movementCommand(String command, String nextChoiceId) {
        this.command = command;
        this.nextChoiceId = nextChoiceId;
    }

    public String getMovementCommand() {
        return command;
    }

    public void setMovementCommand(String movementCommand) {
        this.command = movementCommand;
    }

    public String getNextChoiceId() {
        return nextChoiceId;
    }

    public void setNextChoiceId(String nextChoiceId) {
        this.nextChoiceId = nextChoiceId;
    }
}
