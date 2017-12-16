package parts;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import utils.SceneChanger;

public class BackButtonController {
    @FXML
    private JFXButton backButton;

    @FXML
    public void goBack() {
        SceneChanger.goBack();
    }
}
