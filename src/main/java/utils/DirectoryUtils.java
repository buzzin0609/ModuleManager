package utils;

import com.jfoenix.controls.JFXTextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import settings.AppSettings;

import java.io.File;
import java.util.List;

public class DirectoryUtils {

    public static void chooseDirectory(JFXTextField targetInput, String modalText) {
        chooseDirectory(targetInput, modalText, AppSettings.getInstance().getDefaultPath());
    }

    public static void chooseDirectory(JFXTextField targetInput, String modalText, String initialFolder) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle(modalText);
        chooser.setInitialDirectory(new File(initialFolder));
        File selected = chooser.showDialog(SceneChanger.getStage());

        if (selected != null) {
            targetInput.setText(selected.toString());
        }
    }

    public static void chooseFiles(JFXTextField targetInput, String modalText) {
        chooseFiles(targetInput, modalText, AppSettings.getInstance().getDefaultPath());
    }

    public static void chooseFiles(JFXTextField targetInput, String modalText, String initialFolder) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle(modalText);
        chooser.setInitialDirectory(new File(initialFolder));
        List<File> selected = chooser.showOpenMultipleDialog(SceneChanger.getStage());

        if (selected != null) {
            targetInput.setText(selected.toString().replace("[", "").replace("]", ""));
        }
    }
}
