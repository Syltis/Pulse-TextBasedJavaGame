package Managers;

/*
Has edited this:
- Kristoffer
*/

import Models.Choice;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

// Here the json.simple library is used
public class JSONParsing {

    // Here the google.Gson library is used
    public Choice getChoiceFromJson(int id) {

        JsonParser parser = new JsonParser();
        HashMap<String, Integer> map = new HashMap<>();
        Choice choice = null;

        try {
            JsonArray jsonArr = (JsonArray) parser.parse(new FileReader("src/JSON/Choice.json"));
            JsonObject jsonChoice = (JsonObject) jsonArr.get(id);
            long idLong = jsonChoice.get("id").getAsInt();
            int idInt = (int) idLong;
            String title = jsonChoice.get("title").getAsString();
            String description = jsonChoice.get("description").getAsString();

            JsonArray movementCommands = (JsonArray) jsonChoice.get("availableMovementCommands");
            JsonArray actionCommands = (JsonArray) jsonChoice.get("availableActionCommands");
            JsonArray combatCommands = (JsonArray) jsonChoice.get("availableCombatCommands");

            ArrayList<String> actionCommandList = new ArrayList<>();
            ArrayList<String> combatCommandList = new ArrayList<>();

            for (Object aObject:movementCommands) {
                JsonObject jObject = (JsonObject) aObject;
                map.put(jObject.get("command").toString().replaceAll("\"*\"", ""), jObject.get("nextChoiceId").getAsInt());
            }

            for (Object command:actionCommands) {
                actionCommandList.add(command.toString());
            }

            for (Object command:combatCommands) {
                combatCommandList.add(command.toString());
            }
            choice = new Choice(idInt, title, description, map, actionCommandList, combatCommandList);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return choice;
    }
}