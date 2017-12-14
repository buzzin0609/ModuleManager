package settings;

import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

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
        try {
            JSONObject defaults = new JSONObject();
            defaults.put("moduleFolder", "not set");
            defaults.put("gitUsername", "username");
            defaults.put("gitPassword", "password");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(defaults.toString());
            bw.close();
            addFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJson() {
        return json;
    }
}
