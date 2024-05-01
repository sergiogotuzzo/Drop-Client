package rubik;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class FileManager {
	private static Gson gson = new Gson();
	
	private static File rootDir = new File("rubik");
	private static File modsDir = new File(rootDir, "mods");
	
	public static void init() {
		if (!rootDir.exists()) {
			rootDir.mkdirs();
		}
		
		if (!modsDir.exists()) {
			modsDir.mkdirs();
		}
	}
	
	public static Gson getGson() {
		return gson;
	}
	
	public static File getRootDir() {
		return rootDir;
	}
	
	public static File getModsDir() {
		return modsDir;
	}
	
	public static boolean writeJsonToFile(File file, Object obj) {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			
			fileOutputStream.write(gson.toJson(obj).getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	public static <T extends Object> T readJsonFromFile(File file, Class<T> c) {
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			StringBuilder builder = new StringBuilder();
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line);
			}
			
			bufferedReader.close();
			inputStreamReader.close();
			fileInputStream.close();
			
			return gson.fromJson(builder.toString(), c);
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		}
	}
}
