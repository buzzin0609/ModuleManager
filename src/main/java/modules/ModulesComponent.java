package modules;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import org.apache.commons.io.FileUtils;
import settings.AppSettings;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ModulesComponent {

    private VBox wrapper;
    private File[] moduleFolders;

    public ModulesComponent(VBox wrapper, JFXButton addModuleButton) {
        this.wrapper = wrapper;
        this.moduleFolders = new File(AppSettings.getInstance().getSettingsValue("moduleFolder")).listFiles(File::isDirectory);

        assert moduleFolders != null;
        if (moduleFolders.length > 0) {
            wrapper.getChildren().remove(0);
            render();
        }
    }

    private void render() {
        for (File moduleFolder : moduleFolders) {
            Module module = new Module(moduleFolder);
            wrapper.getChildren().add(module.render());
        }
    }

    public void newModuleInit() {
        NewModuleDialog dialog = new NewModuleDialog();

        Optional<Pair<String, String>> result = dialog.open();

        result.ifPresent(this::handleAddNewModule);
    }

    public void handleAddNewModule(Pair<String, String> folderNameAndPath) {
        File destinationFolder = new File(AppSettings.getInstance().getSettingsValue("moduleFolder") + "/" + folderNameAndPath.getKey());

        String fileNames = folderNameAndPath.getValue();

        for (String fileOrFolder : fileNames.split(",\\s?")) {
            File newFileOrFolder = new File(fileOrFolder);

            try {
                if (newFileOrFolder.isDirectory()) {
                    FileUtils.copyDirectoryToDirectory(newFileOrFolder, destinationFolder);
                } else {
                    FileUtils.copyFileToDirectory(newFileOrFolder, destinationFolder);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
