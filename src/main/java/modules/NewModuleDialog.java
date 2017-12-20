package modules;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.util.Pair;
import settings.AppSettings;
import utils.DirectoryUtils;
import utils.State;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewModuleDialog {

    private Dialog<Pair<String, String>> dialog;
    private JFXTextField folderName;
    private JFXTextField copyFolderPath;
    private List<File> modules;
    private Boolean allowOverwrite = false;
    private String initialFolder;


    public NewModuleDialog(String title, String text) {
        setup(title, text);
        this.initialFolder = AppSettings.getInstance().getDefaultPath();
    }

    public NewModuleDialog(String title, String text, String path, String moduleName) {
        setup(title, text);
        allowOverwrite = true;
        this.initialFolder = path;
        folderName.setText(moduleName);
    }

    private void setup(String title, String text) {
        List<File> modules = (ArrayList<File>) State.getInstance().getValue("modules");
        this.dialog = new Dialog<>();
        this.modules = modules;
        dialog.setTitle(title);
        dialog.setHeaderText(text);

        handleFields();
        addButtons();
    }

    private void addButtons() {
        ButtonType okButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        if (!allowOverwrite) {
            Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
            handleOverwrite(okButton);
        }

        dialog.setResultConverter(button -> {
            if (button == okButtonType) {
                return new Pair<>(folderName.getText(), copyFolderPath.getText());
            }

            return null;
        });
    }

    private void handleOverwrite(Node okButton) {
        okButton.setDisable(true);
        folderName.textProperty().addListener((observable, oldValue, newValue) -> {
            Boolean moduleExists = false;

            for (File module : modules) {
                if (module.getName().toLowerCase().equals(newValue.trim().toLowerCase())) {
                    moduleExists = true;
                }
            }

            okButton.setDisable(newValue.trim().isEmpty() || moduleExists);

            if (moduleExists) {
                folderName.setFocusColor(Paint.valueOf("red"));
            } else {
                folderName.setFocusColor(copyFolderPath.getFocusColor());
            }
        });
    }

    private void handleFields() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        this.folderName = new JFXTextField();
        folderName.setPromptText("Module Name");
        grid.add(new Label("Module Name"), 0, 0);
        grid.add(folderName, 1, 0);

        this.copyFolderPath = new JFXTextField();
        copyFolderPath.setPromptText("Where is your module");
        JFXButton chooseCopyPathButton = new JFXButton("Choose");
        chooseCopyPathButton.setOnMousePressed(e -> {
            DirectoryUtils.chooseFiles(copyFolderPath, "Choose module files", initialFolder);
        });
        HBox folderWrapper = new HBox(10, copyFolderPath, chooseCopyPathButton);
        folderWrapper.setAlignment(Pos.CENTER_LEFT);

        grid.add(new Label("Target Folder Path"), 0, 1);
        grid.add(folderWrapper, 1, 1);

        dialog.getDialogPane().setContent(grid);
    }

    public Optional<Pair<String, String>> open() {
        return dialog.showAndWait();
    }
}
