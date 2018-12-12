package Managers;

import Models.Item;
import Models.Scenario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collection;

/*
Parses Json with the Google.gson-library. Parses the Json-objects to java-objects.
- Parses either a single scenario/item, or the whole lists
 */
public final class JSONParsing {

    private JSONParsing()
    {
        throw new UnsupportedOperationException();
    }

    public static Scenario getScenarioFromJson(String id) {
        Scenario newScenario = null;
        Type scenarioType = new TypeToken<Collection<Scenario>>(){}.getType();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/JSON/ScenarioNEW.json"));
            Gson gson = new GsonBuilder().create();
            Collection<Scenario> scenarios = gson.fromJson(reader, scenarioType);
            if (!scenarios.isEmpty()) {
                for (Scenario aScenario : scenarios) {
                    if (aScenario.getId().equals(id)) {
                        newScenario = aScenario;
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newScenario;
    }

    public static Collection<Scenario> getScenarioListFromJson() {
        Collection<Scenario> scenarios = null;
        Type scenarioType = new TypeToken<Collection<Scenario>>(){}.getType();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/JSON/ScenarioNEW.json"));
            Gson gson = new GsonBuilder().create();
            scenarios = gson.fromJson(reader, scenarioType);
            if (!scenarios.isEmpty()) {
                return scenarios;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scenarios;
    }

    public static Item getItemFromJson(String id) {
        Item newItem = null;
        Type itemType = new TypeToken<Collection<Item>>(){}.getType();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/JSON/Item.json"));
            Gson gson = new GsonBuilder().create();
            Collection<Item> items = gson.fromJson(reader, itemType);
            if (!items.isEmpty()) {
                for (Item aItem: items) {
                    if (aItem.getItemName().equals(id)) {
                        newItem = aItem;
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newItem;
    }

    public static Collection<Item> getItemListFromJson() {
        Collection<Item> items = null;
        Type itemType = new TypeToken<Collection<Item>>(){}.getType();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/JSON/Item.json"));
            Gson gson = new GsonBuilder().create();
            items = gson.fromJson(reader, itemType);
            if (!items.isEmpty()) {
                return items;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }

    /*
    - Old parser, could still be of use later. Uses the json.simple library

    public Scenario getChoiceFromJson(int id) {

        JsonParser parser = new JsonParser();
        HashMap<String, Integer> map = new HashMap<>();
        Scenario choice = null;

        try {
            JsonArray jsonArr = (JsonArray) parser.parse(new FileReader("src/JSON/Scenario.json"));
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
            choice = new Scenario(idInt, title, description, map, actionCommandList, combatCommandList);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return choice;
    }
    */
}