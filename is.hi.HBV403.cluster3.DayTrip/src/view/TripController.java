package view;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class TripController implements Initializable {
    @FXML
    private JFXTabPane tabs;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void makeTab() {
        final Tab tab = new Tab("Tab " + (tabs.getTabs().size() + 1));
        tab.setText("Tripname");
        BorderPane borderpane = new BorderPane();
        Label heading = new Label("Name of Trip");
        heading.setFont(new Font("System", 32));
        heading.setStyle("-fx-font-weight: bold");
        borderpane.setTop(heading);
        BorderPane.setAlignment(heading, Pos.CENTER);
        tab.setContent(borderpane);
        tabs.getTabs().add(tab);
        tabs.getSelectionModel().select(tab);
    }
}
