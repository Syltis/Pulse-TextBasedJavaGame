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
            ArrayList<String> actionCommandList = new ArrayList<String>();
            // TODO et specific jsonObjects from actionCommands and then set arrayList
            int len = actionCommands.size();
            for (int i=0;i<len;i++){
                actionCommandList.add(actionCommands.get(i).toString());
            }
            System.out.println(actionCommandList);
            Situation situation = new Situation();
            situation.setId(idInt);
            situation.setDescription(description);
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
