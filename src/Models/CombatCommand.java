package Models;

/*
Has edited this:
- Kristoffer
*/

public class CombatCommand {

	/*
	Has edited this:
	- Kristoffer
	*/

	private String command;
	private String combatResult;

	public CombatCommand(String command, String combatResult) {
		this.command = command;
		this.combatResult = combatResult;
	}

	public String getCombatCommand() { return command; }

	public void setCombatCommand(String command) { this.command = command; }

	public String getCombatResult() {
		return combatResult;
	}

	public void setCombatResult(String actionResult) {
		this.combatResult = actionResult;
	}
}
