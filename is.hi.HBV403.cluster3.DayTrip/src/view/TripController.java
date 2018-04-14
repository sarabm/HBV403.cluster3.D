package view;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Trip;

import java.net.URL;
import java.util.ResourceBundle;

public class TripController implements Initializable {
    @FXML
    private JFXTabPane tabs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void makeTab(Trip trip) {
        final Tab tab = new Tab("Tab " + (tabs.getTabs().size() + 1));
        tab.setText(trip.tripName);
        BorderPane borderpane = new BorderPane();
        Label heading = new Label(trip.tripName);
        Button button = new Button();
        Text text = new Text();
        button.setText("Book");

        text.setText("Lýsing: \n" + trip.tripDescription + "\n\n");
        if (trip.tripPrice > 0) {
            text.setText(text.getText() + "Verð: \n" + trip.tripPrice + "\n\n");
        }
        if (trip.tripLocation != null) {
            text.setText(text.getText() + "Staðsetning: \n" + trip.tripLocation + "\n\n");
        }
        if (trip.tripDifficulty > 0) {
            text.setText(text.getText() + "Erfiðleiki: \n" + trip.tripDifficulty + "\n\n");
        }
        heading.setFont(new Font("System", 32));
        heading.setStyle("-fx-font-weight: bold");
        borderpane.setTop(heading);
        BorderPane.setAlignment(heading, Pos.CENTER);
        borderpane.setBottom(button);
        BorderPane.setAlignment(button, Pos.TOP_CENTER);
        borderpane.setCenter(text);
        BorderPane.setAlignment(text, Pos.CENTER);
        tab.setContent(borderpane);
        tabs.getTabs().add(tab);
        tabs.getSelectionModel().select(tab);
    }
}
