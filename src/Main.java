import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
        deleteButton.getStyleClass().add("button-delete");

        Button mockDataButton = new Button("Add mockup data");

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
                carList.removeIf(car -> car.toString().equals(selectedCarString));
                updateListView();
            } else {
                showAlert("No Selection", "Please select a car to delete.");
            }
        });

        mockDataButton.setOnAction(e -> {
            initialize();
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
                listView,
                mockDataButton
        );

        //scene
        Scene scene = new Scene(layout, 500, 800);

        //load css
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        //get icon
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));

        primaryStage.setScene(scene);
        primaryStage.show();

        //set focus on layout to not select any field so prompts are displayed
        layout.requestFocus();
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

    //method for mock up data
    public void initialize() {
        // Initialize the carList with some pre-defined cars
        carList.add(new Car("B:ABC123", "Toyota", "Corolla", 132));
        carList.add(new Car("B:BCD234", "Honda", "Civic", 158));
        carList.add(new Car("B:CDE345", "Ford", "Mustang", 450));
        carList.add(new Car("B:DEF456", "Mercedes", "SL500", 435));
        carList.add(new Car("B:EFG567", "Opel", "Manta", 81));
        updateListView();
    }

    //launch main
    public static void main(String[] args) {
        launch(args);
    }
}