package utils;

import com.jfoenix.controls.JFXTextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import settings.AppSettings;

import java.io.File;
import java.util.List;

public class DirectoryUtils {

    public static void chooseDirectory(JFXTextField targetInput, String modalText) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle(modalText);
        chooser.setInitialDirectory(new File(AppSettings.getInstance().getDefaultPath()));
        File selected = chooser.showDialog(SceneChanger.getStage());

        if (selected != null) {
            targetInput.setText(selected.toString());
        }
    }

    public static void chooseFiles(JFXTextField targetInput, String modalText) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle(modalText);
        chooser.setInitialDirectory(new File(AppSettings.getInstance().getDefaultPath()));
        List<File> selected = chooser.showOpenMultipleDialog(SceneChanger.getStage());

        if (selected != null) {
            targetInput.setText(selected.toString().replace("[", "").replace("]", ""));
        }
    }
}
