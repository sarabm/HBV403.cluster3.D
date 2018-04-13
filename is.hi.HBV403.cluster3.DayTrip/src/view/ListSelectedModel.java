package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionModel;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Trip;

import java.io.IOException;
import java.util.List;
import static storage.DBManager.getTrip;


public class ListSelectedModel implements ChangeListener {
    private ListController listController;
    private FXMLLoader viewingTripLoader;
    private List<Trip> tripList;
    private long tripid;
    private Trip trip;
    public ListSelectedModel(ListController lc, List<Trip> trips) {
        this.listController = lc;
        this.tripList = trips;
    }
    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        Parent root;
        Stage stage;
        System.out.println(oldValue);
        for(Trip t : tripList) {
            if (newValue.toString().equals(t.tripName)) {
                trip = t;
            }
        }
        stage = new Stage();
        if(!listController.isViewingTrip()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                root = loader.load(getClass().getResource("Trip.fxml").openStream());
                viewingTripLoader = loader;
                Scene scene = new Scene(root, 820, 535);
                stage.setScene(scene);
                stage.show();
                stage.setX(stage.getX() + 200);
                stage.setY(stage.getY() + 200);
                listController.setViewingTrip(true);
                TripController viewingTripWindow =  this.viewingTripLoader.getController();
                viewingTripWindow.makeTab(trip);
                stage.setOnCloseRequest((WindowEvent event1) -> {
                    listController.setViewingTrip(false);
                });

            } catch(IOException ex) {
                System.err.println("Error while loading Trip.fxml "+ex.getMessage());
            }
        }
        else {
            TripController viewingTripWindow =  this.viewingTripLoader.getController();
            viewingTripWindow.makeTab(trip);

        }
    }
}
