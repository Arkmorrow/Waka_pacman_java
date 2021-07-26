package ghost;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class configRead{
    /**
    * read in the config.json file and 
    * search the content we want and return a String
    * check if has the element, if not, return a
    * empty String. If the file is not find,
    * return a empty string.
    * @param configName the name of the config file
    * @param element element we want to search from config.json,
    * @return String value found for the element we input,
    * return empty string when not find
    */
    public static String readJson(String configName, String element){

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try{

            FileReader reader = new FileReader(configName);

            //Read JSON file
            JSONObject content  = (JSONObject) jsonParser.parse(reader);

            //check if has the element in the file
            if (content.get(element) == null){
                return "";
            }

            //get element detail
            String elements = content.get(element).toString();

            return elements;
            
        } catch (IOException a) {
            return "";
        } catch (ParseException b) {
            return "";
        }
    }

    /**
    * input the map file and 
    * loop throught the map file and return a array of String
    * check if file is exit or not ,if not ,return null
    * @param filename map file
    * @return a array of String
    */
    public static String[] readMap(String filename){
        try{
            File f = new File(filename);
            Scanner scan = new Scanner(f);
            String contentString = "";

            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                contentString += line;
            }

            scan.close();
            String[] contentList = new String[contentString.length()];

            for (int i = 0;i<contentString.length();i++){
                contentList[i] = Character.toString(contentString.charAt(i));
            }

            return contentList;
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}