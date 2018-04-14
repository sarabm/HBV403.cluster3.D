package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateTripController implements Controller {
    private String fxmlPrev;
    @FXML
    private JFXButton confirmSignUp;

    public void bookingService() throws IOException {
        Stage stage = (Stage) confirmSignUp.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("BookingService.fxml"), "UpdateTrip.fxml");
    }

    public void createTrip() throws IOException {
        Stage stage = (Stage) confirmSignUp.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("CreateTrip.fxml"), "UpdateTrip.fxml");
    }

    public void searchPage() throws IOException {
        Stage stage = (Stage) confirmSignUp.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("SearchWindow.fxml"), "");
    }

    public void back() throws IOException {
        Stage stage = (Stage) confirmSignUp.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource(fxmlPrev), "UpdateTrip.fxml");
    }

    @Override
    public void setPrev(String prev) {
        this.fxmlPrev = prev;
    }
}
