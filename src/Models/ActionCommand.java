package Models;

public class ActionCommand {

	/*
	Has edited this:
	- Kristoffer
	*/

	private String command;
	private String actionResult;

	public ActionCommand(String command, String actionResult) {}

	public String getActionCommand() { return command; }

	public void setActionCommand(String command) { this.command = command; }

	public String getActionResult() {
		return actionResult;
	}

	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}
}
