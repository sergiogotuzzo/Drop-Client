package rubik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	private static final File rootDir = new File("rubik");
    private static final File modsFile = new File(rootDir, "mods.json");
        
    public static void init() {
		if (!rootDir.exists()) {
			rootDir.mkdirs();
		}
		
		try {
			if (!modsFile.exists()) {
				modsFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static File getModsFile() {
    	return modsFile;
    }

    public static void set(String key, Object value) {
        JSONObject jsonObject = readJsonFromFile();
        
        jsonObject.put(key, value);
        
        writeJsonToFile(jsonObject);
    }

    public static Object get(String key) {
        JSONObject jsonObject = readJsonFromFile();
        
        return jsonObject.get(key);
    }
    
    public static boolean has(String key) {
        JSONObject jsonObject = readJsonFromFile();
        
        return jsonObject.containsKey(key);
    }

    private static JSONObject readJsonFromFile() {
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader(modsFile.getPath())) {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            
            return new JSONObject();
        }
    }

    private static void writeJsonToFile(JSONObject jsonObject) {
        try (FileWriter file = new FileWriter(modsFile.getPath())) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
