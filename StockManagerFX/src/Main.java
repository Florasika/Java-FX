import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ProductManager manager =
            new ProductManager();

    @Override
    public void start(Stage stage) {

        manager.loadProducts();

        TextField idField =
                new TextField();

        idField.setPromptText("ID");

        TextField nameField =
                new TextField();

        nameField.setPromptText("Name");

        TextField quantityField =
                new TextField();

        quantityField.setPromptText("Quantity");

        TextField priceField =
                new TextField();

        priceField.setPromptText("Price");

        Button addButton =
                new Button("Add Product");

        TableView<Product> table =
                new TableView<>();

        TableColumn<Product,Integer> idCol =
                new TableColumn<>("ID");

        idCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getId()
                ).asObject());

        TableColumn<Product,String> nameCol =
                new TableColumn<>("Name");

        nameCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getName()
                ));

        TableColumn<Product,Integer> quantityCol =
                new TableColumn<>("Quantity");

        quantityCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getQuantity()
                ).asObject());

        TableColumn<Product,Double> priceCol =
                new TableColumn<>("Price");

        priceCol.setCellValueFactory(data ->
                new SimpleDoubleProperty(
                        data.getValue().getPrice()
                ).asObject());

        table.getColumns().addAll(
                idCol,
                nameCol,
                quantityCol,
                priceCol
        );

        table.setItems(
                manager.getProducts()
        );

        addButton.setOnAction(e -> {

            try {

                Product product =
                        new Product(
                                Integer.parseInt(idField.getText()),
                                nameField.getText(),
                                Integer.parseInt(quantityField.getText()),
                                Double.parseDouble(priceField.getText())
                        );

                manager.addProduct(product);

                idField.clear();
                nameField.clear();
                quantityField.clear();
                priceField.clear();

            } catch (Exception ex) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Invalid data"
                        );

                alert.showAndWait();
            }
        });

        Button deleteButton =
                new Button("Delete Selected");

        deleteButton.setOnAction(e -> {

            Product selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if(selected != null){
                manager.deleteProduct(selected);
            }
        });

        VBox root =
                new VBox(
                        10,
                        idField,
                        nameField,
                        quantityField,
                        priceField,
                        addButton,
                        deleteButton,
                        table
                );

        Scene scene =
                new Scene(root,700,500);

        stage.setTitle("Stock Manager");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}