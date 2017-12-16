package utils;

import com.jfoenix.controls.JFXTextField;
import javafx.stage.DirectoryChooser;
import settings.AppSettings;

import java.io.File;

public class DirectoryUtils {

    public static void chooseDirectory(JFXTextField targetInput) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Set project directory");
        chooser.setInitialDirectory(new File(AppSettings.getInstance().getDefaultPath()));
        File selected = chooser.showDialog(SceneChanger.getStage());

        if (selected != null) {
            targetInput.setText(selected.toString());
        }
    }
}
