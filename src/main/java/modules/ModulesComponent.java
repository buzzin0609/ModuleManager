package modules;

import javafx.scene.layout.VBox;
import javafx.util.Pair;
import org.apache.commons.io.FileUtils;
import project.ManageProjectController;
import settings.AppSettings;
import utils.State;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ModulesComponent {

    private VBox wrapper;
    private List<File> moduleFolders;

    public ModulesComponent(VBox wrapper) {
        this.wrapper = wrapper;
        this.moduleFolders = new ArrayList<>(Arrays.asList(
                Objects.requireNonNull(
                        new File(AppSettings.getInstance()
                                .getSettingsValue("moduleFolder"))
                                .listFiles(File::isDirectory)
                )
        ));

        if (moduleFolders.size() > 0) {
            wrapper.getChildren().remove(0);
            render();
        }

        State.getInstance().setValue("modules", moduleFolders);
    }

    private void render() {
        for (File moduleFolder : moduleFolders) {
            Module module = new Module(moduleFolder);
            wrapper.getChildren().add(module.render());
        }
    }

    public void newModuleInit() {
        NewModuleDialog dialog = new NewModuleDialog("Add New Module", "Let's add a new module");

        Optional<Pair<String, String>> result = dialog.open();

        result.ifPresent(this::handleAddNewModule);
    }

    public void handleAddNewModule(Pair<String, String> folderNameAndPath) {

        File destinationFolder = new File(AppSettings.getInstance().getSettingsValue("moduleFolder") + "/" + folderNameAndPath.getKey());

        String fileNames = folderNameAndPath.getValue();

        Arrays.stream(fileNames.split(",\\s?"))
                .map(File::new)
                .forEach(newFileOrFolder -> {
                    ManageProjectController.copyFileOrFolder(destinationFolder, newFileOrFolder);
                });

        moduleFolders.add(destinationFolder);
        wrapper.getChildren().add(new Module(destinationFolder).render());

    }
}
