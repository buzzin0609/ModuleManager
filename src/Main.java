import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import settings.AppSettings;

public class Main extends Application {

    private MainController mainController = new MainController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        AppSettings.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
        primaryStage.setTitle("Module Manager");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        mainController.start(primaryStage, root);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
