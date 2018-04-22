package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingConfirmController implements Controller {
    private String fxmlPrev;

    @FXML
    private Label label;


    @Override
    public void setPrev(String prev) {
        this.fxmlPrev = prev;
    }
}
