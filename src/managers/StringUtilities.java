package managers;

import models.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Utility class with static methods for working on strings.
 */

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
		string = string.replaceAll("[-!@#$%^&*(),.?\":{}|<>+/']+", "");
		string = string.replaceAll("[ ]{2,}", " ");
		string = string.toLowerCase();
		return string;
	}

	// Checks if string starts with 'use', which means the player wantes to make use of an item
	static boolean commandIsUse(String playerCommand) {
		boolean isUse = false;
		if (playerCommand.substring(0, 3).equalsIgnoreCase("use")) isUse = true;
		return isUse;
	}

	// Builds an anemy from a string
	// Here the string 'rat211', will result in a 'Rat' enemy with the stats STR-2, CON-1, LCK-1
	static Enemy generateEnemyFromCombatResult(String combatResult) {
		Enemy enemy = null;
		Pattern p = Pattern.compile("([a-z]+)([0-9]+)");
		Matcher m = p.matcher(combatResult);
		if (!m.find())
		{
			return enemy;
		}
		String characterName = m.group(1);
		String characterSkills = m.group(2);
		int[] skills = new int[]
				{
						Integer.parseInt(characterSkills.substring(0,1)),
						Integer.parseInt(characterSkills.substring(1,2)),
						Integer.parseInt(characterSkills.substring(2,3))
				};

		enemy = new Enemy
				(
						characterName,
						"An actual" + characterName,
						100,
						skills,
						5
				);
		return enemy;
	}
}