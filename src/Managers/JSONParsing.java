package Managers;

import DataTransferObjects.Situation;
import org.json.simple.parser.JSONParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONParsing {

    public Situation getSituationFromJson(int situationId) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArr = (JSONArray) parser.parse(new FileReader("src/JSON/Situations.json"));
            JSONObject jsonSituation = (JSONObject) jsonArr.get(situationId);
            long idLong = (long) jsonSituation.get("id");
            int idInt = (int) idLong;
            String description = (String) jsonSituation.get("description");
            Situation situation = new Situation();
            situation.setId(idInt);
            situation.setDescription(description);
            return situation;
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
