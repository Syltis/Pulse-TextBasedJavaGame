package Managers;

import DataTransferObjects.Choice;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
Has edited this:
- Kristoffer
*/

/*
Parses JSON to a Choice-object.
Receives an array of objects and puts them in JSONArrays
  and converts that to an array and sends it to the choice-object. -kris
 */
public class JSONParsing {

    public Choice getChoiceFromJson(int choiceId) {
        JSONParser parser = new JSONParser();
        try {
            //Fetch data into array, narrow down Choice-object, get correct filetype of Id and Choice.
            JSONArray jsonArr = (JSONArray) parser.parse(new FileReader("src/JSON/Choices.json"));
            JSONObject jsonChoice = (JSONObject) jsonArr.get(choiceId);
            long idLong = (long) jsonChoice.get("id");
            int idInt = (int) idLong;
            String description = (String) jsonChoice.get("description");

            // Fetch available commands for the choice
            JSONArray actionCommands = (JSONArray) jsonChoice.get("availableActionCommands");
            JSONArray commandTargets = (JSONArray) jsonChoice.get("availableCommandTargets");
            ArrayList<String> actionCommandList = new ArrayList<>();
            ArrayList<String> commandTargetList = new ArrayList<>();

            // Fetch the command and instantiate a Choice
            for (Object command:actionCommands) {
                actionCommandList.add(command.toString());
            }
            for (Object command:commandTargets) {
                commandTargetList.add(command.toString());
            }
            Choice choice = new Choice();
            choice.setId(idInt);
            choice.setDescription(description);
            choice.setAvailableActionCommands(actionCommandList);
            choice.setAvailableCommandTargets(commandTargetList);
            return choice;

            // Exceptions stacked from most spesific to the least specific
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
