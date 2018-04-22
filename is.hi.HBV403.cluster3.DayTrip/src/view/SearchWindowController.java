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
    private JFXTextField searchString;

    public void forwardButton() throws IOException {
        ListController.setLocation(searchString.getText());
        Stage stage = (Stage) forwardbutton.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("List.fxml"), "SearchWindow.fxml");
    }

    public void createTrip() throws IOException {
        Stage stage = (Stage) forwardbutton.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("CreateTrip.fxml"), "SearchWindow.fxml");
    }


    @Override
    public void setPrev(String prev) {

    }
}
