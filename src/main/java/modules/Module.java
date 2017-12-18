package modules;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.File;

public class Module {

    private File moduleFolder;

    public Module(File moduleFolder) {
        this.moduleFolder = moduleFolder;
    }

    public HBox render() {
        Label name = new Label(moduleFolder.getName());
        HBox wrapper = new HBox(20.0, name, editButton());
        wrapper.setAlignment(Pos.CENTER_LEFT);
        return wrapper;
    }

    public JFXButton editButton() {
        JFXButton button = new JFXButton("Edit");
        button.setOnMousePressed(e -> {
            button.setText("clicked");
        });

        return button;
    }

}
