package settings;

import com.jfoenix.transitions.JFXFillTransition;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController {

    @FXML
    private AnchorPane settingsWrapper;

    public SettingsController() {
    }

    @FXML
    private void changeScene() {
        SceneChanger.changeScene("/fxml/layout.fxml");
    }
}
