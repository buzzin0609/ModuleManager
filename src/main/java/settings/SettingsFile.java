package settings;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SettingsFile {
    private JSONObject json;
    private String filePath;

    public SettingsFile(String filePath) {
        this.filePath = filePath;
        addFile();
    }

    private void addFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            json = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            createNewFile(new File(filePath));
        }
    }

    private void createNewFile(File file) {
        JSONObject defaults = new JSONObject();
        defaults.put("moduleFolder", "not set");
        defaults.put("gitUsername", "username");
        defaults.put("gitPassword", "password");
        defaults.put("gitRepo", "not set");
        writeToFile(file, defaults.toString());
        addFile();
    }

    private Boolean writeToFile(File file, String content) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public JSONObject getJson() {
        return json;
    }

    public Boolean updateFile(JSONObject newSettings) {
        json = newSettings;
        return writeToFile(new File(filePath), newSettings.toString());
    }
}
