package modules;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.io.File;
import java.util.Optional;

public class Module {

    private File moduleFolder;

    public Module(File moduleFolder) {
        this.moduleFolder = moduleFolder;
    }

    public HBox render() {
        Label name = new Label(moduleFolder.getName());
        HBox wrapper = new HBox(20.0, name, editButton());
        wrapper.setAlignment(Pos.CENTER_LEFT);
        return wrapper;
    }

    public JFXButton editButton() {
        JFXButton button = new JFXButton("Edit");
        button.setOnMousePressed(e -> {
            NewModuleDialog dialog = new NewModuleDialog("Edit Module", "Update an existing module", moduleFolder.getAbsolutePath(), moduleFolder.getName());
            Optional<Pair<String, String>> result = dialog.open();

            result.ifPresent(this::handleEditModule);
        });

        return button;
    }

    private void handleEditModule(Pair<String, String> folderNameAndPath) {
        Pair<String, String> foo = folderNameAndPath;
    }

}
