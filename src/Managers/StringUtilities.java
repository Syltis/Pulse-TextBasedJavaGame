package Managers;

/*
Has edited this:
- Kristoffer
*/

/*
Utility class for working on strings. All static methods and no object instatiation
 */

import java.util.ArrayList;
import java.util.List;

public final class StringUtilities {

	// Private constructor so the class cannot be instantiated
	private StringUtilities()
	{
		throw new UnsupportedOperationException();
	}

	public static List<String> splitCommand(String input) {
		String actionCommand = input.substring(0, input.indexOf(" "));
		String commandTarget = input.substring(input.indexOf(" "), input.length());
		ArrayList<String> splitCommandList = new ArrayList<>();
		splitCommandList.add(actionCommand);
		splitCommandList.add(cleanString(commandTarget));
		return splitCommandList;
	}

	public static String cleanString(String string) {
		string = string.trim();
		string = string.replaceAll("[-!@#$%^&*(),.?\":{}|<>0-9+/']+", "");
		string = string.replaceAll("[ ]{2,}", " ");
		string = string.toLowerCase();
		return string;
	}
}