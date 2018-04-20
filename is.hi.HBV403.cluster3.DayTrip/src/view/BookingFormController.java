package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Trip;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingFormController implements Controller, Initializable {
    private String fxmlPrev;
    @FXML
    private Label label;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label price;
    @FXML
    private Label locale;
    @FXML
    private JFXComboBox comboBox;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    private String newLabel;
    private String newStart;
    private String newEnd;
    private String newPrice;
    private String newLocation;
    private String newFirstName;
    private String newMail;
    private String newLastName;
    private Integer newNoCust;

    public void bookConfirm() throws IOException {
        Stage stage = (Stage) label.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("BookingConfirmed.fxml"), "BookingForm.fxml");
    }

    public void makeBooking(Trip trip) {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setTitle(trip.tripName);
        this.newStart = trip.tripStartDate.toString();
        this.newLabel = trip.tripName;
        this.newEnd = trip.tripEndDate.toString();
        this.newPrice = ""+trip.tripPrice;
        this.newLocation = trip.tripLocation;
        label.setText("Heiti ferðar \n" + this.newLabel + "\n");
        startDate.setText("Upphafsdagsetning: \n" + this.newStart + "\n");
        endDate.setText("Endadagsetning: \n" + this.newEnd + "\n");
        price.setText("Verð ferðar: \n " + this.newPrice + "\n");
        locale.setText("Staðsetning ferðar: \n" + this.newLocation);
    }

    @Override
    public void setPrev(String prev) {
        this.fxmlPrev = prev;
    }

    public void setNoGuests(JFXComboBox combobox) {
        ArrayList<String> diffList = new ArrayList();
        for(int i=1; i<20; i++) {
            diffList.add(""+i);
        }
        ObservableList<String> obs = FXCollections.observableArrayList(diffList);
        combobox.setItems(obs);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setNoGuests(comboBox);
        comboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            this.newNoCust = Integer.parseInt((String)newValue);
        });
        firstName.textProperty().addListener((observable, oldValue, newValue) -> {
            this.newFirstName = newValue;
        });
        lastName.textProperty().addListener((observable, oldValue, newValue) -> {
            this.newLastName = newValue;
        });
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            this.newMail = newValue;
        });
    }
}
