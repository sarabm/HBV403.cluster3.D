package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import model.*;
import storage.DBManager;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;


public class CreateTripController implements Controller, Initializable {

    private String fxmlPrev;
    @FXML
    private JFXButton createNewTrip;
    @FXML
    private TextField title;
    @FXML
    private TextField location;
    @FXML
    private TextField personName;
    @FXML
    private TextField personEmail;
    @FXML
    private NumericTextField price;
    @FXML
    private JFXCheckBox couplesTrip;
    @FXML
    private JFXCheckBox familyTrip;
    @FXML
    private JFXCheckBox groupTrip;
    @FXML
    private JFXCheckBox wheelchairAccess;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private NumericTextField availableSeats;
    @FXML
    private ComboBox difficultyCombo;
    @FXML
    private TextField description;


    public void searchPage() throws IOException{
        Stage stage = (Stage) createNewTrip.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("SearchWindow.fxml"),"");
    }

    public void back() throws IOException {
        Stage stage = (Stage) createNewTrip.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource(fxmlPrev),"CreateTrip.fxml");
    }

    public void createNewTrip() throws IOException {
        Stage stage = (Stage) createNewTrip.getScene().getWindow();
        if(title.getText().isEmpty() || dateTo.getValue() == null || dateFrom.getValue() == null ||
                location.getText().isEmpty() || personName.getText().isEmpty() || personEmail.getText().isEmpty()
                || price.getText().isEmpty() || availableSeats.getText().isEmpty()
                || difficultyCombo.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not complete");
            alert.setContentText("Entire form must be filled");
            alert.showAndWait();
            return;
        }
        LocalDate localDateFrom = dateFrom.getValue();
        Instant instant1 = Instant.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()));
        Date startDate = Date.from(instant1);

        LocalDate localDateTo = dateTo.getValue();
        Instant instant2 = Instant.from(localDateTo.atStartOfDay(ZoneId.systemDefault()));
        Date endDate = Date.from(instant2);

        Trip newTrip = new Trip(
                title.getText(),
                startDate,
                endDate,
                description.getText(),
                Double.parseDouble(price.getText()),
                Integer.parseInt(difficultyCombo.getValue().toString()),
                wheelchairAccess.isSelected(),
                familyTrip.isSelected(),
                couplesTrip.isSelected(),
                groupTrip.isSelected(),
                null,
                location.getText(),
                Integer.parseInt(availableSeats.getText())
        );
        if(DBManager.addTrip(newTrip)){
            DayTripUI.changeStage(stage, getClass().getResource("TripCreated.fxml"),"CreateTrip.fxml");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not successful");
            alert.setContentText("Trip creation failed. Please try again later");
            alert.showAndWait();
            return;
        }

    }


    @Override
    public void setPrev(String prev) {
        this.fxmlPrev = prev;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        difficultyCombo.getItems().addAll(
                "1",
                "2",
                "3",
                "4",
                "5"
            );
    }
}


