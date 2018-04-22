package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
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
    private TextField price;
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
    private TextField availableSeats;
    @FXML
    private ComboBox difficultyCombo;
    @FXML
    private TextField description;



    public void bookingService() throws IOException{
        Stage stage = (Stage) createNewTrip.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("BookingService.fxml"),"CreateTrip.fxml");
    }
    public void updateTrip() throws IOException {
        Stage stage = (Stage) createNewTrip.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("UpdateTrip.fxml"),"CreateTrip.fxml");
    }
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
        // Meðhöndla ef null
        // Trip newTrip = Trip((null, title.getText(), dateFrom.getValue(), Date tripEndDate, description.getText(), double(price.getText()),
        // int(difficultyCombo).getValue(), wheelchairAccess.isSelected(), familyTrip.isSelected(), couplesTrip.isSelected(),
        // groupTrip.isSelected(), null, location.getText(), int(availableSeats.getText())));
        //title.getText()
        DayTripUI.changeStage(stage, getClass().getResource("TripCreated.fxml"),"CreateTrip.fxml");
    }



    @Override
    public void setPrev(String prev) {
        this.fxmlPrev = prev;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        difficultyCombo.getItems().addAll(
                1,
                2,
                3,
                4,
                5
            );
    }
}
