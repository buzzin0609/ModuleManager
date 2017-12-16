package parts;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utils.SceneChanger;

public class HeaderComponent {

    private Text headerTitle;
    private Text headerStandFirst;

    public HeaderComponent(String title, String standFirst, Pane target) {
        headerTitle = (Text) target.lookup("#headerTitle");
        headerStandFirst = (Text) target.lookup("#headerStandFirst");

        headerTitle.setText(title);
        headerStandFirst.setText(standFirst);
    }

}
