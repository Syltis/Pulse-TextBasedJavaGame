package Managers;

/*
Has edited this:
- Kristoffer
*/

/*
Utility class for working on strings. All static methods and no object instatiation
 */

import Models.EnemyCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static EnemyCharacter getCharacterFromCombatResult(String combatResult) {
		Pattern p = Pattern.compile("([a-z]+)([0-9]+)");
		Matcher m = p.matcher(combatResult);
		if (!m.find())
		{
			System.out.println("nomatch");
		}
		String characterName = m.group(1);
		String characterSkills = m.group(2);
		int[] skills = new int[3];
		String skill1 = characterSkills.substring(0,1);
		String skill2 = characterSkills.substring(1,2);
		String skill3 = characterSkills.substring(2,3);
		skills[0] = Integer.parseInt(skill1);
		skills[1]  = Integer.parseInt(skill2);
		skills[2]  = Integer.parseInt(skill3);

		// TODO: This is where we are now.
		EnemyCharacter newCharacter = new EnemyCharacter(characterName, "An actual" + characterName, 100, skills, 5);
		return newCharacter;
	}
}