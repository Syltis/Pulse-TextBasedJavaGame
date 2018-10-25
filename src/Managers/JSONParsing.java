package Managers;

import DataTransferObjects.Situation;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
Parses JSON to a Situation-object.
Receives an array of objects and puts them in JSONArrays
    and converts that to an array and sends it to the situation-object. -kris
 */
public class JSONParsing {

    public Situation getSituationFromJson(int situationId) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArr = (JSONArray) parser.parse(new FileReader("src/JSON/Situations.json"));
            JSONObject jsonSituation = (JSONObject) jsonArr.get(situationId);
            long idLong = (long) jsonSituation.get("id");
            int idInt = (int) idLong;
            String description = (String) jsonSituation.get("description");
            JSONArray actionCommands = (JSONArray) jsonSituation.get("availableActionCommands");
            JSONArray commandTargets = (JSONArray) jsonSituation.get("availableCommandTargets");
            ArrayList<String> actionCommandList = new ArrayList<>();
            ArrayList<String> commandTargetList = new ArrayList<>();
            for (Object command:actionCommands) {
                actionCommandList.add(command.toString());
            }
            for (Object command:commandTargets) {
                commandTargetList.add(command.toString());
            }
            Situation situation = new Situation();
            situation.setId(idInt);
            situation.setDescription(description);
            situation.setAvailableActionCommands(actionCommandList);
            situation.setAvailableCommandTargets(commandTargetList);
            return situation;
        }
        catch (FileNotFoundException e) {
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
