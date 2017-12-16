package settings;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;
import parts.HeaderComponent;
import utils.DirectoryUtils;
import utils.SceneChanger;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private AnchorPane settingsWrapper;

    @FXML
    private JFXTextField githubUser;

    @FXML
    private JFXPasswordField githubPassword;

    @FXML
    private JFXTextField moduleFolder;

    @FXML
    private JFXButton saveButton;

    @FXML
    private AnchorPane mainHeader;

    public void initialize(URL location, ResourceBundle resources) {
        githubUser.setText(AppSettings.getInstance().getSettingsValue("gitUsername"));
        githubPassword.setText(AppSettings.getInstance().getSettingsValue("gitPassword"));
        moduleFolder.setText(AppSettings.getInstance().getSettingsValue("moduleFolder"));
        new HeaderComponent("Settings", "Edit application settings", mainHeader);
    }

    public SettingsController() {
    }

    @FXML
    private void changeScene() {
        SceneChanger.goBack();
    }

    @FXML
    public void chooseModuleFolder() {
        DirectoryUtils.chooseDirectory(moduleFolder);
    }

    @FXML
    public void saveSettings() {
        saveButton.setText("Saving...");
        JSONObject newSettings = new JSONObject();
        newSettings.put("gitUsername", githubUser.getText());
        newSettings.put("gitPassword", githubPassword.getText());
        newSettings.put("moduleFolder", moduleFolder.getText());

        if (AppSettings.getInstance().updateSettings(newSettings)) {
            saveButton.setText("Success");

        }

    }
}
