

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import settings.AppSettings;
import utils.SceneChanger;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private JFXTextField directoryInput;

    @FXML
    private JFXButton directoryButton;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private String directoryInputText() {
        return AppSettings.getInstance().getDefaultPath();
    }

    @FXML
    private void handleDirectoryButton(MouseEvent event) {

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Set project directory");
        chooser.setInitialDirectory(new File(AppSettings.getInstance().getDefaultPath()));
        File selected = chooser.showDialog(SceneChanger.getStage());

        if (selected != null) {
            directoryInput.setText(selected.toString());
        }

    }

    @FXML
    private void handleSettingsButton(MouseEvent event) {
        SceneChanger.changeScene("/fxml/settings.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        directoryInput.setText(directoryInputText());
        directoryInput.requestFocus();
    }
}
