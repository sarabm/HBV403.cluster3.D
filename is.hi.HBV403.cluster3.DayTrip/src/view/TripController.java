package view;

import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Trip;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class TripController implements Initializable {
    @FXML
    private JFXTabPane tabs;

    private BookingFormController listController;
    private FXMLLoader viewingTripLoader;
    private List<Trip> tripList;
    private long tripid;
    private Trip trip;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handle(Trip trip) throws IOException {
        Parent root;
        Stage stage = (Stage) tabs.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader();
            root = loader.load(getClass().getResource("BookingForm.fxml").openStream());
            viewingTripLoader = loader;
            Scene scene = new Scene(root, 620, 435);
            stage.setScene(scene);
            stage.show();
            BookingFormController viewingTripWindow = this.viewingTripLoader.getController();
            viewingTripWindow.makeBooking(trip);
        } catch (IOException ex) {
            System.err.println("Error while loading Trip.fxml " + ex.getMessage());
        }
    }

    public void makeTab(Trip trip) {
        final Tab tab = new Tab("Tab " + (tabs.getTabs().size() + 1));
        tab.setText(trip.tripName);
        BorderPane borderpane = new BorderPane();
        Label heading = new Label(trip.tripName);
        Button button = new Button();
        Text text = new Text();
        button.setText("Book");
        button.setOnAction(event -> {
            try {
                handle(trip);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        text.setText("Lýsing: \n" + trip.tripDescription + "\n\n");
        if (trip.tripPrice > 0) {
            text.setText(text.getText() + "Verð: \n" + trip.tripPrice + "\n\n");
            text.setWrappingWidth(250);
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
