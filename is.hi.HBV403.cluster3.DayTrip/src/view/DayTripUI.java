package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DayTripUI extends Application {

    public static void changeStage(Stage stage, URL url, String prev) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();
        Controller controller = loader.getController();
        controller.setPrev(prev);
        Scene scene = new Scene(root, 820, 535);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SearchWindow.fxml"));
        primaryStage.setTitle("daytrip.exe");
        primaryStage.setScene(new Scene(root, 820, 535));
        primaryStage.show();
    }

}
