package parts;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
