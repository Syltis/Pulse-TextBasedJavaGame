package Managers;

/*
Has edited this:
- Kristoffer
*/

import Models.Choice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collection;

// Here the json.simple library is used
public class JSONParsing {

    public Choice getChoiceFromJsonV2(String id) {
        BufferedReader reader;
        Choice newChoice = new Choice();
        Type choiceType = new TypeToken<Collection<Choice>>(){}.getType();

        try {
            reader = new BufferedReader(new FileReader("src/JSON/Choice.json"));
            Gson gson = new GsonBuilder().create();
            Collection<Choice> choices = gson.fromJson(reader, choiceType);
            if (!choices.isEmpty()) {
                for (Choice choice1:choices) {
                    if (choice1.getId().equals(id)) {
                        newChoice = choice1;
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newChoice;

    }

    /*
    - Old parser, could still be of use later.

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
        }

        return choice;
    }
    */
}