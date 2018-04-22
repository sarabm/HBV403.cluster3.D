package view;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import control.TripsController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Filter;
import model.Trip;
import storage.DBManager;


import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static storage.DBManager.getAllTrips;

public class ListController implements Controller, Initializable {
    private static String location;
    private static boolean viewingTrip;
    @FXML
    private ListView list;
    @FXML
    private JFXTextField searchWindow;
    @FXML
    private JFXComboBox maxPrice;
    @FXML
    private JFXComboBox minPrice;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
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
    private static String searchString;
    private Filter myFilter = new Filter();

    public static void setLocation(String loc) {
        searchString = loc;
    }

    public boolean isViewingTrip() {
        return this.viewingTrip;
    }

    public void setViewingTrip(boolean viewingTrip) {
        this.viewingTrip = viewingTrip;
    }


    private void setPriceList(JFXComboBox comboBox) {
        int max = 0;
        ArrayList<String> priceList = new ArrayList();
        List<Trip> trips = DBManager.getAllTrips();
        for(Trip trip: trips) {
            if(trip.tripPrice > max) {
                max = (int)trip.tripPrice;
            }
        }
        int price = 0;
        for (int i = 0; i < 5; i++) {
            priceList.add("" + price);
            price += 1000;
            if(price > max) {
                priceList.add("" + price);
                ObservableList<String> obs = FXCollections.observableArrayList(priceList);
                comboBox.setItems(obs);
                return;
            }
        }
        price += 5000;
        priceList.add("" + price);
        if(price > max) {
            ObservableList<String> obs = FXCollections.observableArrayList(priceList);
            comboBox.setItems(obs);
            return;
        }
        for (int i = 0; i < 9; i++) {
            price += 10000;
            priceList.add("" + price);
            if(price > max) {
                ObservableList<String> obs = FXCollections.observableArrayList(priceList);
                comboBox.setItems(obs);
                return;
            }
        }
        ObservableList<String> obs = FXCollections.observableArrayList(priceList);
        comboBox.setItems(obs);
    }

    private void setNumbOfCustomers(JFXComboBox comboBox) {
        int count = 0;
        ArrayList<String> custList = new ArrayList();
        for (int i = 0; i < 50; i++) {
            custList.add("" + count);
            count += 1;
        }
        ObservableList<String> obs = FXCollections.observableArrayList(custList);
        comboBox.setItems(obs);
    }

    private void setDifficulty(JFXComboBox comboBox) {
        ArrayList<String> diffList = new ArrayList();
        diffList.add("0");
        diffList.add("1");
        diffList.add("2");
        diffList.add("3");
        diffList.add("4");
        diffList.add("5");
        ObservableList<String> obs = FXCollections.observableArrayList(diffList);
        comboBox.setItems(obs);
    }


    public void createTrip() throws IOException {
        Stage stage = (Stage) searchWindow.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("CreateTrip.fxml"), "List.fxml");
    }

    public void searchPage() throws IOException {
        Stage stage = (Stage) searchWindow.getScene().getWindow();
        DayTripUI.changeStage(stage, getClass().getResource("SearchWindow.fxml"), "");
    }

    public void search() {
        this.myFilter.searchString = this.searchWindow.getText();
        filterOut(this.myFilter);
        this.searchString = this.searchWindow.getText();
    }


    @Override
    public void setPrev(String prev) {
    }

    public void filterOut(Filter filter) {
        List<Trip> trips = TripsController.searchTrips(filter);
        ObservableList<Trip> data = FXCollections.observableArrayList(trips);
        list.setItems(data);
    }

    public Filter getMyFilter() {
        return this.myFilter;
    }
    public void setMyFilter(Filter mf) {
        this.myFilter = mf;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.myFilter.searchString = this.searchString;
        List<Trip> trips = TripsController.searchTrips(myFilter);
        ObservableList<Trip> data = FXCollections.observableArrayList(trips);
        list.setItems(data);
        searchWindow.setText(this.searchString);
        setPriceList(maxPrice);
        setPriceList(minPrice);
        setNumbOfCustomers(numbOfCustomers);
        setDifficulty(difficulty);
        difficulty.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            myFilter.tripDifficulty = Integer.parseInt((String)newValue);
            filterOut(myFilter);
        });
        numbOfCustomers.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            myFilter.customers = Integer.parseInt((String)newValue);
            filterOut(myFilter);
        });
        maxPrice.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            Float value = Float.parseFloat((String)newValue);
            myFilter.priceMax = value;
            filterOut(myFilter);
            changePriceList(Math.round(value), false);
        });
        minPrice.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            Float value = Float.parseFloat((String)newValue);
            myFilter.priceMin = value;
            filterOut(myFilter);
            changePriceList(Math.round(value), true);
        });

        list.getSelectionModel().selectedItemProperty().addListener(new ListSelectedModel(this, data));
        System.out.println(list.selectionModelProperty());

        couplesTrip.selectedProperty().addListener(new ListenerForCheckbox(this,couplesTrip, "coupleFriendly"));
        familyTrip.selectedProperty().addListener(new ListenerForCheckbox(this,couplesTrip, "familyFriendly"));
        groupTrip.selectedProperty().addListener(new ListenerForCheckbox(this,couplesTrip, "groupFriendly"));
        wheelChairAccess.selectedProperty().addListener(new ListenerForCheckbox(this,couplesTrip, "wheelChairAccess"));
    }

    private void changePriceList(int value, boolean changeMaxPriceList) {
        JFXComboBox comboBox = null;
        if(changeMaxPriceList) comboBox = maxPrice;
        else comboBox = minPrice;
        ObservableList<String> obs = comboBox.getItems();
        List<String> elements = new ArrayList<String>();
        if(changeMaxPriceList) {
            for(String price: obs) {
                if(Integer.parseInt(price) < value) {
                    elements.add(price);
                }
            }
        } else {
            for(String price: obs) {
                if(Integer.parseInt(price) > value) {
                    elements.add(price);
                }
            }
        }
        obs.removeAll(elements);
        comboBox.setItems(obs);
    }


    public void from() {
        LocalDate localDate = dateFrom.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        myFilter.tripStartDate = date;
        filterOut(myFilter);
    }

    public void to() {
        LocalDate localDate = dateTo.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        myFilter.tripEndDate = date;
        filterOut(myFilter);
    }

}
