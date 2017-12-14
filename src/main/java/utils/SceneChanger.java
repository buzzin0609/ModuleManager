package utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Method;

public class SceneChanger {
    private static SceneChanger ourInstance = new SceneChanger();

    public static SceneChanger getInstance() {
        return ourInstance;
    }

    private static Stage stage;
    private static Application mainClass;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        stage.setScene(new Scene(root, 600, 400));
    }

    public static Stage getStage() {
        return stage;
    }
}
