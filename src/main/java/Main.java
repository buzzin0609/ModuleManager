import javafx.application.Application;
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
        SceneChanger.changeScene("/fxml/main.fxml");
        primaryStage.setTitle("Modularize.It");


//        mainController.start(primaryStage, root);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
