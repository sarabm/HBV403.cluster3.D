package view;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TripCreatedController implements Controller {
    private String fxmlPrev;
    @FXML
    private FlowPane tripCreatedPane;


    public void createTrip() throws IOException {
        Stage stage = (Stage) tripCreatedPane.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("CreateTrip.fxml"),"TripCreated.fxml");
    }
    public void searchPage() throws IOException{
        Stage stage = (Stage) tripCreatedPane.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("SearchWindow.fxml"),"");
    }
    public void back() throws IOException {
        Stage stage = (Stage) tripCreatedPane.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource(fxmlPrev),"TripCreated.fxml");
    }

    @Override
    public void setPrev(String prev) {
        this.fxmlPrev = prev;
    }

}