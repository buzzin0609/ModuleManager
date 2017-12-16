package utils;

import com.sun.org.apache.xerces.internal.xs.StringList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SceneChanger {
    private static SceneChanger ourInstance = new SceneChanger();

    public static SceneChanger getInstance() {
        return ourInstance;
    }

    private static Stage stage;
    private static Application mainClass;
    private static Parent currentRoot;
    private static List<String> history = new ArrayList<String>() {};


    private SceneChanger() {
    }

    public static void setStage(Stage _stage, Application aClass) {
        stage = _stage;
        mainClass = aClass;
    }

    public static void changeScene(String sceneName) {
        Parent root = null;
        try {
            root = FXMLLoader.load(mainClass.getClass().getResource(sceneName));
            currentRoot = root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        history.add(sceneName);
    }

    public static Stage getStage() {
        return stage;
    }

    public static Parent getCurrentRoot() {
        return currentRoot;
    }

    public static void goBack() {
        //remove this one from the stack, then the previous is the one before that.
        history.remove(history.size() - 1);
        String previous = history.remove(history.size() - 1);

        changeScene(previous);
    }


}
