

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import settings.AppSettings;
import utils.SceneChanger;

public class Main extends Application {

//    private MainController mainController = new MainController();
//    private Class mainClass;


    @Override
    public void start(Stage primaryStage) throws Exception{
        SceneChanger.setStage(primaryStage, this);
        AppSettings.getInstance();
        Parent root = FXMLLoader.load(this.getClass().getResource("/fxml/layout.fxml"));
        primaryStage.setTitle("Module Manager");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

//        mainController.start(primaryStage, root);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
