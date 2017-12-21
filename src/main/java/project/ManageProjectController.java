package project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import parts.HeaderComponent;
import settings.AppSettings;
import utils.DirectoryUtils;
import utils.State;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ManageProjectController implements Initializable {

    @FXML
    private AnchorPane mainHeader;

    @FXML
    private Text projectFolderText;

    @FXML
    private JFXTextField targetFolder;

    @FXML
    private JFXTextField moduleToAdd;

    @FXML
    private JFXButton addModuleButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new HeaderComponent("Manage Project", "Click the modules to add to your project", mainHeader);
        String projectFolder = (String) State.getInstance().getValue("projectFolder");
        projectFolderText.setText(projectFolder);
        targetFolder.setText(projectFolder);
    }

    @FXML
    public void chooseTarget() {
        DirectoryUtils.chooseDirectory(targetFolder, "Destination folder", projectFolderText.getText());
    }

    @FXML
    public void startAddModule() {
        DirectoryUtils.chooseFiles(moduleToAdd, "Choose modules to add", AppSettings.getInstance().getDefaultModuleFolder());
    }

    public Boolean addModule() {
        if (moduleToAdd.getText().isEmpty() || targetFolder.getText().isEmpty()) {
            return false;
        }

        addModuleButton.setText("Adding");

        String fileNames = moduleToAdd.getText();
        File targetFolderFile = new File(targetFolder.getText());
        Arrays.stream(fileNames.split(",\\s?"))
                .map(File::new)
                .forEach(newFileOrFolder -> {
                    copyFileOrFolder(targetFolderFile, newFileOrFolder);
                });

        addModuleButton.setText("Add Module");

        return true;
    }

    public static void copyFileOrFolder(File targetFolderFile, File newFileOrFolder) {
        try {
            if (newFileOrFolder.isDirectory()) {
                FileUtils.copyDirectoryToDirectory(newFileOrFolder, targetFolderFile);
            } else {
                FileUtils.copyFileToDirectory(newFileOrFolder, targetFolderFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
