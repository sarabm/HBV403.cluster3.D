package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateTripController implements Controller{
    private String fxmlPrev;
    @FXML
    private JFXButton updateTrip;
    @FXML
    private TextField emailAddress;
    @FXML
    private TextField tripNumber;

    public void bookingService() throws IOException{
        Stage stage = (Stage) updateTrip.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("BookingService.fxml"),"UpdateTrip.fxml");
    }
    public void createTrip() throws IOException {
        Stage stage = (Stage) updateTrip.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("CreateTrip.fxml"),"UpdateTrip.fxml");
    }
    public void searchPage() throws IOException{
        Stage stage = (Stage) updateTrip.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("SearchWindow.fxml"),"");
    }
    public void back() throws IOException {
        Stage stage = (Stage) updateTrip.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource(fxmlPrev),"UpdateTrip.fxml");
    }
    public void getTrip() throws IOException {
        Stage stage = (Stage) updateTrip.getScene().getWindow();
        String email = emailAddress.getText();
        String tripNo = tripNumber.getText();
        System.out.println("Trip Number: " + tripNo);
        System.out.println("Email: " + email);
        DayTripUI.changeStage(stage, getClass().getResource("BookingInfo.fxml"),"BookingService.fxml");
    }


    @Override
    public void setPrev(String prev) {
        this.fxmlPrev = prev;
    }
}
