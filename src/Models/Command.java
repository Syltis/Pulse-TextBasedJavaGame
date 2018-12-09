package Models;

public class Command {

	private String command;
	private String result;

	public Command(String command, String result)
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

	public void setResult(String result) {
		this.result = result;
	}
}
