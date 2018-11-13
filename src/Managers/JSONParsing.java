package Managers;

/*
Has edited this:
- Kristoffer
*/

import Models.Choice;
import Models.ChoiceV2;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Here the json.simple library is used
public class JSONParsing {

    public Choice getChoiceFromJson(int choiceId) {
        JSONParser parser = new JSONParser();
        try {
            // Fetch data into array, narrow down Choice-object, get correct filetype of Id and Choice.
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

            // Exceptions stacked from most specific to least specific
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

    // Here the google.Gson library is used
    public ChoiceV2 getChoiceFromJsonV2(int id) {

        JsonParser parser = new JsonParser();
        HashMap<String, Integer> map = new HashMap<>();
        ChoiceV2 choice = null;

        try {
            JsonArray jsonArr = (JsonArray) parser.parse(new FileReader("src/JSON/ChoiceV2.json"));
            JsonObject jsonChoice = (JsonObject) jsonArr.get(id);
            long idLong = jsonChoice.get("id").getAsInt();
            int idInt = (int) idLong;
            String description = jsonChoice.get("description").getAsString();

            JsonArray movementCommands = (JsonArray) jsonChoice.get("availableMovementCommands");
            JsonArray actionCommands = (JsonArray) jsonChoice.get("availableActionCommands");
            JsonArray combatCommands = (JsonArray) jsonChoice.get("availableCombatCommands");

            ArrayList<String> actionCommandList = new ArrayList<>();
            ArrayList<String> combatCommandList = new ArrayList<>();

            for (Object aObject:movementCommands) {
                JsonObject jObject = (JsonObject) aObject;
                map.put(jObject.get("command").toString(), jObject.get("nextChoiceId").getAsInt());
            }

            for (Object command:actionCommands) {
                actionCommandList.add(command.toString());
            }

            for (Object command:combatCommands) {
                combatCommandList.add(command.toString());
            }
            choice = new ChoiceV2(idInt, description, map, actionCommandList, combatCommandList);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return choice;
    }
}