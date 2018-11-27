package Models;

public class MovementCommand {

    private String command;
    private String nextScenarioId;

    public MovementCommand(String command, String nextScenarioId)
    {
        this.command = command;
        this.nextScenarioId = nextScenarioId;
    }

    public String getMovementCommand() {
        return command;
    }

    public void setMovementCommand(String movementCommand) {
        this.command = movementCommand;
    }

    public String getNextScenarioId() {
        return nextScenarioId;
    }

    public void setNextScenarioId(String nextScenarioId) {
        this.nextScenarioId = nextScenarioId;
    }
}
