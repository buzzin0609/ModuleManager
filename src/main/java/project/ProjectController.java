package project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import parts.HeaderComponent;
import settings.AppSettings;
import utils.DirectoryUtils;
import utils.SceneChanger;
import utils.State;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectController implements Initializable {

    @FXML
    private JFXTextField directoryInput;

    @FXML
    private JFXButton directoryButton;

    @FXML
    private AnchorPane mainHeader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        directoryInput.setText(directoryInputText());
        directoryInput.requestFocus();
        new HeaderComponent("Modularize your project", "Choose your project directory and start adding", mainHeader);
    }

    @FXML
    private String directoryInputText() {
        return AppSettings.getInstance().getDefaultPath();
    }

    @FXML
    private void handleDirectoryButton(MouseEvent event) {
        DirectoryUtils.chooseDirectory(directoryInput, "Set project directory");
    }

    @FXML
    private void manageProject() {
        State.getInstance().setValue("projectFolder", directoryInput.getText());
        SceneChanger.changeScene("/fxml/project/manageProject.fxml");
    }
}
