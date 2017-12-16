

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import parts.HeaderComponent;
import utils.SceneChanger;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private JFXButton settingsButton;

    @FXML
    private AnchorPane mainHeader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new HeaderComponent("Modularize.it", "Quick and easy way to manage project dependencies offline", mainHeader);

    }

    @FXML
    private void handleSettingsButton(MouseEvent event) {
        SceneChanger.changeScene("/fxml/settings.fxml");
    }

    @FXML
    private void goToProject() {
        SceneChanger.changeScene("/fxml/project/project.fxml");
    }

    @FXML
    private void goToModules() {
        SceneChanger.changeScene("/fxml/modules/modules.fxml");
    }
}
