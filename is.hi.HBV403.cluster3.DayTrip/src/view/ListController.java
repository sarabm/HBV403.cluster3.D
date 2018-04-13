package view;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Trip;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static storage.DBManager.getAllTrips;

public class ListController implements Controller, Initializable {
    private static String location;
    private static boolean viewingTrip;
    @FXML
    private ListView list;
    @FXML
    private JFXTextField locationTXT;
    @FXML
    private JFXComboBox maxPrice;
    @FXML
    private JFXComboBox minPrice;
    @FXML
    private JFXComboBox dateFrom;
    @FXML
    private JFXComboBox dateTo;
    @FXML
    private JFXComboBox numbOfCustomers;
    @FXML
    private JFXComboBox difficulty;
    @FXML
    private JFXCheckBox couplesTrip;
    @FXML
    private JFXCheckBox familyTrip;
    @FXML
    private JFXCheckBox groupTrip;
    @FXML
    private JFXCheckBox wheelChairAccess;

    public static void setLocation(String loc) {
        location = loc;
    }

    public boolean isViewingTrip() {
        return this.viewingTrip;
    }

    public void setViewingTrip(boolean viewingTrip) {
        this.viewingTrip = viewingTrip;
    }


    private void setPriceList(JFXComboBox comboBox) {
        int price = 0;
        ArrayList<String> priceList = new ArrayList();
        for(int i = 0; i<5; i++) {
            priceList.add("" + price);
            price += 10000;
        }
        for(int i = 0; i<9; i++) {
            priceList.add("" + price);
            price += 50000;
        }
        ObservableList<String> obs = FXCollections.observableArrayList(priceList);
        comboBox.setItems(obs);
    }

    private void setNumbOfCustomers(JFXComboBox comboBox) {
        int count = 0;
        ArrayList<String> custList = new ArrayList();
        for(int i = 0; i<50; i++) {
            custList.add("" + count);
            count += 1;
        }
        ObservableList<String> obs = FXCollections.observableArrayList(custList);
        comboBox.setItems(obs);
    }

    private void setDifficulty(JFXComboBox comboBox) {
        ArrayList<String> diffList = new ArrayList();
        diffList.add("Mjög létt");
        diffList.add("Létt");
        diffList.add("Miðlungs");
        diffList.add("Erfitt");
        diffList.add("Mjög erfitt");
        ObservableList<String> obs = FXCollections.observableArrayList(diffList);
        comboBox.setItems(obs);
    }


    public void bookingService() throws IOException {
        Stage stage = (Stage) locationTXT.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("BookingService.fxml"),"List.fxml");
    }

    public void createTrip() throws IOException {
        Stage stage = (Stage) locationTXT.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("CreateTripController.fxml"),"List.fxml");
    }

    public void updateTrip() throws IOException {
        Stage stage = (Stage) locationTXT.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("UpdateTrip.fxml"),"List.fxml");
    }

    public void searchPage() throws IOException{
        Stage stage = (Stage) locationTXT.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("SearchWindow.fxml"),"");
    }


    @Override
    public void setPrev(String prev) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Trip> data = FXCollections.observableArrayList();
        List<Trip> trips = getAllTrips();
        for ( Trip t : trips) {
            data.add(t);
            System.out.println(t.tripID);
        }
        list.setItems(data);
        locationTXT.setText(this.location);
        setPriceList(maxPrice);
        setPriceList(minPrice);
        setNumbOfCustomers(numbOfCustomers);
        setDifficulty(difficulty);
        difficulty.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
                    System.out.println(newValue);
        });
        numbOfCustomers.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
                    System.out.println(newValue);
        });
        maxPrice.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
                    System.out.println(newValue);
        });
        minPrice.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
                    System.out.println(newValue);
        });
        list.getSelectionModel().selectedItemProperty().addListener(new ListSelectedModel(this));
        System.out.println(list.selectionModelProperty());
    }

}
