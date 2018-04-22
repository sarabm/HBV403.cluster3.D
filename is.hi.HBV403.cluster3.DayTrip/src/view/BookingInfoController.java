package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookingInfoController implements Controller, Initializable {
    private String fxmlPrev;
    @FXML
    private FlowPane bookingInfoPane;


    public void bookingService() throws IOException{
        Stage stage = (Stage) bookingInfoPane.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("BookingService.fxml"),"BookingInfo.fxml");
    }
    public void createTrip() throws IOException {
        Stage stage = (Stage) bookingInfoPane.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("CreateTrip.fxml"),"BookingInfo.fxml");
    }
    public void updateTrip() throws IOException {
        Stage stage = (Stage) bookingInfoPane.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("UpdateTrip.fxml"),"BookingInfo.fxml");
    }
    public void searchPage() throws IOException{
        Stage stage = (Stage) bookingInfoPane.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("SearchWindow.fxml"),"");
    }
    public void back() throws IOException {
        Stage stage = (Stage) bookingInfoPane.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource(fxmlPrev),"BookingInfo.fxml");
    }

    @Override
    public void setPrev(String prev) {
        this.fxmlPrev = prev;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Text text = new Text("Halló");
        bookingInfoPane.getChildren().add(text);
    }
}