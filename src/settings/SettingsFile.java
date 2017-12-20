package settings;

public class SettingsFile {
    private static SettingsFile ourInstance = new SettingsFile();

    public static SettingsFile getInstance() {
        return ourInstance;
    }

    private SettingsFile() {
    }
}
