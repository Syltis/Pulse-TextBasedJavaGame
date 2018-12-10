package Models;

public class Command {

	private String command;
	private final String result;

	Command(String command, String result)
	{
		this.command = command;
		this.result = result;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getResult() {
		return result;
	}
}
