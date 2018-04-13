package view;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchWindowController implements Controller{
    @FXML
    private Button forwardbutton;

    @FXML
    private JFXTextField locationTXT;

    public void forwardButton() throws IOException {
        ListController.setLocation(locationTXT.getText());
        Stage stage = (Stage) forwardbutton.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("List.fxml"),"SearchWindow.fxml");
    }
    public void createTrip() throws IOException {
        Stage stage = (Stage) forwardbutton.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("CreateTrip.fxml"),"SearchWindow.fxml");
    }
    public void updateTrip() throws IOException {
        Stage stage = (Stage) forwardbutton.getScene().getWindow();
<<<<<<< HEAD
        DayTripUI.changeStage(stage, getClass().getResource("CreateTrip.fxml"),"SearchWindow.fxml");
=======
        DayTripUI.changeStage(stage, getClass().getResource("UpdateTrip.fxml"),"SearchWindow.fxml");
    }
    public void bookingService() throws IOException {
        Stage stage = (Stage) forwardbutton.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("BookingService.fxml"),"SearchWindow.fxml");
>>>>>>> 58d4b21f8c0b196bed309dd02d9f8f37493bdda0
    }

    @Override
    public void setPrev(String prev) {

    }
}
