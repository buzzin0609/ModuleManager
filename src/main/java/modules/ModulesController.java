package modules;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import parts.HeaderComponent;
import settings.AppSettings;

import java.net.URL;
import java.util.ResourceBundle;

public class ModulesController implements Initializable {

    @FXML
    private AnchorPane mainHeader;

    @FXML
    private VBox modulesWrapper;

    @FXML
    private JFXButton addModuleButton;

    @FXML
    private JFXButton syncButton;

    private ModulesComponent modulesComponent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new HeaderComponent("Modules", "Manage your modules here", mainHeader);
        this.modulesComponent = new ModulesComponent(modulesWrapper);
        String repo = AppSettings.getInstance().getSettingsValue("gitRepo");
        if (repo.equals("not set") || repo.isEmpty()) {
            syncButton.setOpacity(0);
        }
    }

    @FXML
    public void addModule() {
        modulesComponent.newModuleInit();
    }

    @FXML
    public void syncRepo() {
        String repo = AppSettings.getInstance().getSettingsValue("gitRepo");
    }
}
