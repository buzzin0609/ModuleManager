package settings;

import org.json.JSONObject;

import java.io.File;

public class AppSettings {
    private static AppSettings ourInstance = new AppSettings();
    public static AppSettings getInstance() {
        return ourInstance;
    }

    private String defaultPath = System.getProperty("user.home");
    private String dataFolder = defaultPath + "/.ModularizeIt";
    private String defaultModuleFolder = dataFolder + "/modules";
    private String settingsFilePath = dataFolder + "/settings.json";
    private SettingsFile settingsFile;

    private AppSettings() {
        setupDataFolder();
    }

    private void setupDataFolder() {
        File _dataFolder = new File(dataFolder);

        if (!_dataFolder.exists()) {
            _dataFolder.mkdir();
            setupDataContents();
        } else {
            setupDataContents();
        }

    }

    private void setupDataContents() {
        this.settingsFile = new SettingsFile(settingsFilePath);
        JSONObject settings = settingsFile.getJson();
        String moduleFolder = settings.getString("moduleFolder");
        File _moduleFolder;

        if (moduleFolder.equals("not set")) {
            moduleFolder = defaultModuleFolder;
        }

        _moduleFolder = new File(moduleFolder);



        if (!_moduleFolder.exists()) {
            _moduleFolder.mkdir();
        }
    }

    public String getDefaultPath() {
        return defaultPath;
    }

    public String getDataFolder() {
        return dataFolder;
    }

    public String getDefaultModuleFolder() {
        return defaultModuleFolder;
    }

    public String getSettingsValue(String key) {
        return settingsFile.getJson().getString(key);
    }

    public Boolean updateSettings(JSONObject newSettings) {
        return settingsFile.updateFile(newSettings);
    }

}
