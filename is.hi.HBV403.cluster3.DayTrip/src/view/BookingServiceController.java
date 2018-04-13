package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingServiceController implements Controller{
    private String fxmlPrev;
    @FXML
    private JFXButton logIn;

    public void createTrip() throws IOException {
        Stage stage = (Stage) this.logIn.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("CreateTrip.fxml"),"BookingService.fxml");
    }
    public void updateTrip() throws IOException {
        Stage stage = (Stage)logIn.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("UpdateTrip.fxml"),"BookingService.fxml");
    }
    public void searchPage() throws IOException{
        Stage stage = (Stage) logIn.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("SearchWindow.fxml"),"");
    }
    public void back() throws IOException {
        Stage stage = (Stage) this.logIn.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource(fxmlPrev),"BookingService.fxml");
    }
    @Override
    public void setPrev(String prev) {
        this.fxmlPrev = prev;
    }
}
