package settings;

import java.io.File;

public class AppSettings {
    private static AppSettings ourInstance = new AppSettings();

    public static AppSettings getInstance() {
        return ourInstance;
    }

    private String defaultPath = System.getProperty("user.home");
    private String dataFolder = System.getProperty("user.home") + "/.ModuleManager";

    private AppSettings() {
        setupDataFolder();
    }

    private void setupDataFolder() {
        File _dataFolder = new File(dataFolder);

        if (_dataFolder == null || !_dataFolder.exists()) {
            _dataFolder.mkdir();
        }
    }

    public String getDefaultPath() {
        return defaultPath;
    }
}
