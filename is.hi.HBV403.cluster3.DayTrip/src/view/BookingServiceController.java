package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingServiceController implements Controller{
    private String fxmlBack;
    @FXML
    private JFXButton logIn;
    @Override
    public void setPrev(String prev) {
        this.fxmlBack = prev;
    }
    public void back() throws IOException {
        Stage stage = (Stage) this.logIn.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource(fxmlBack),"BookingService.fxml");
    }
    public void createTrip() throws IOException {
        Stage stage = (Stage) this.logIn.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("CreateTrip.fxml"),"BookingService.fxml");
    }
    public void searchPage() throws IOException{
        Stage stage = (Stage) logIn.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("SearchWindow.fxml"),"");
    }
}
