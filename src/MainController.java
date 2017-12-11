import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import settings.AppSettings;

import java.io.File;

public class MainController {
    private Stage primaryStage;

    public void start(Stage primaryStage, Parent root) {
        this.primaryStage = primaryStage;
        handleDirectoryInput(root);
    }

    private void handleDirectoryInput(Parent root) {
        JFXTextField input = handleMainInput(root);
        handleDirectoryButton(input, root);
    }

    private JFXTextField handleMainInput(Parent root) {
        JFXTextField dirInput = (JFXTextField) root.lookup("#directoryInput");
        dirInput.setText(AppSettings.getInstance().getDefaultPath());
        dirInput.isFocused();

        return dirInput;
    }

    private void handleDirectoryButton(JFXTextField input, Parent root) {
        JFXButton dirButton = (JFXButton) root.lookup("#directoryButton");
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Set project directory");
        chooser.setInitialDirectory(new File(AppSettings.getInstance().getDefaultPath()));
        dirButton.setOnMousePressed(event -> {
            File selected = chooser.showDialog(primaryStage);

            if (selected != null) {
                input.setText(selected.toString());
            }

        });
    }

}
