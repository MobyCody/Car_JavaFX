import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private ArrayList<Car> carList = new ArrayList<>();
    private ListView<String> listView = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Car Manager");

        //layout
        VBox layout = new VBox(10);

        //input fields
        TextField idField = new TextField();
        idField.setPromptText("ID");

        TextField brandField = new TextField();
        brandField.setPromptText("Brand");

        TextField modelField = new TextField();
        modelField.setPromptText("Model");

        TextField horsePowerField = new TextField();
        horsePowerField.setPromptText("HP");

        Button addButton = new Button("Add Car");

        Button deleteButton = new Button("Delete selected Car");

        //event handling
        addButton.setOnAction(e -> {
            try {
                String id = idField.getText();
                String brand = brandField.getText();
                String model = modelField.getText();
                int horsePower = Integer.parseInt(horsePowerField.getText());
                Car car  = new Car(id, brand, model, horsePower);
                carList.add(car);
                updateListView();
                clearFields(idField, brandField, modelField, horsePowerField);
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter valid data for all fields.");
            }
        });

        deleteButton.setOnAction(e -> {
            String selectedCarString = listView.getSelectionModel().getSelectedItem();
            if (selectedCarString != null) {
                carList.removeIf(car ->toString().equals(selectedCarString));
                updateListView();
            } else {
                showAlert("No Selection", "Please select a car to delete.");
            }
        });

        //adding components to layout
        layout.getChildren().addAll(
                new Label ("Car Details:"),
                idField,
                brandField,
                modelField,
                horsePowerField,
                addButton,
                deleteButton,
                new Label("Cars:"),
                listView
        );

        //scene
        Scene scene = new Scene(layout, 500, 500);

        //load css
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //method to update list view by iterating over carList
    private void updateListView() {
        listView.getItems().clear();
        for (Car car : carList) {
            listView.getItems().add(car.toString());
        }
    }

    //method to clear all fields
    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    //method to display alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //launch main
    public static void main(String[] args) {
        launch(args);
    }
}