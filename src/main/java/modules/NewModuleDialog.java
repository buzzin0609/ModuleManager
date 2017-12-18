package modules;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
import utils.DirectoryUtils;

import java.util.Optional;

public class NewModuleDialog {

    private Dialog<Pair<String, String>> dialog;
    private JFXTextField folderName;
    private JFXTextField copyFolderPath;

    public NewModuleDialog() {
        this.dialog = new Dialog<>();
        dialog.setTitle("Add New Module");
        dialog.setHeaderText("Let's add a new module");

        addButtons();
        handleFields();

    }

    private void addButtons() {
        ButtonType okButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == okButton) {
                return new Pair<>(folderName.getText(), copyFolderPath.getText());
            }

            return null;
        });
    }

    private void handleFields() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        this.folderName = new JFXTextField();
        folderName.setPromptText("New Module Name");
        grid.add(new Label("Module Name"), 0, 0);
        grid.add(folderName, 1, 0);

        this.copyFolderPath = new JFXTextField();
        copyFolderPath.setPromptText("Where is your module");
        JFXButton chooseCopyPathButton = new JFXButton("Choose");
        chooseCopyPathButton.setOnMousePressed(e -> {
            DirectoryUtils.chooseFiles(copyFolderPath, "Choose module files");
        });
        HBox folderWrapper = new HBox(10, copyFolderPath, chooseCopyPathButton);
        folderWrapper.setAlignment(Pos.CENTER_LEFT);

        grid.add(new Label("Targer Folder Path"), 0, 1);
        grid.add(folderWrapper, 1, 1);

        dialog.getDialogPane().setContent(grid);
    }

    public Optional<Pair<String, String>> open() {
        return dialog.showAndWait();
    }
}
