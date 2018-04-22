package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.control.*;
import java.io.IOException;

public class BookingServiceController implements Controller{
    private String fxmlPrev;
    @FXML
    private JFXButton getBooking;
    @FXML
    private TextField bookingNumber;
    @FXML
    private TextField emailAddress;

    public void createTrip() throws IOException {
        Stage stage = (Stage) this.getBooking.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("CreateTrip.fxml"),"BookingService.fxml");
    }
    public void updateTrip() throws IOException {
        Stage stage = (Stage)getBooking.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("UpdateTrip.fxml"),"BookingService.fxml");
    }
    public void searchPage() throws IOException{
        Stage stage = (Stage) getBooking.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("SearchWindow.fxml"),"");
    }
    public void back() throws IOException {
        Stage stage = (Stage) this.getBooking.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource(fxmlPrev),"BookingService.fxml");
    }

    public void getMyBooking() throws IOException {
        Stage stage = (Stage) getBooking.getScene().getWindow();
        String email = emailAddress.getText();
        String bookingNo = bookingNumber.getText();
        System.out.println("Booking Number: " + bookingNo);
        System.out.println("Email: " + email);
        DayTripUI.changeStage(stage, getClass().getResource("BookingInfo.fxml"),"BookingService.fxml");
    }

    @Override
    public void setPrev(String prev) {
        this.fxmlPrev = prev;
    }
}
