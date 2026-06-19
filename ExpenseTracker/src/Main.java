import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ObservableList<Expense> expenses =
            FXCollections.observableArrayList();

    private Label totalLabel =
            new Label("Total : 0.0 €");

    @Override
    public void start(Stage stage) {

        TextField descriptionField =
                new TextField();

        descriptionField.setPromptText(
                "Description"
        );

        TextField amountField =
                new TextField();

        amountField.setPromptText(
                "Montant"
        );

        Button addButton =
                new Button("Ajouter");

        TableView<Expense> table =
                new TableView<>();

        TableColumn<Expense, String> descriptionColumn =
                new TableColumn<>("Description");

        descriptionColumn.setCellValueFactory(
                data ->
                        new ReadOnlyObjectWrapper<>(
                                data.getValue()
                                        .getDescription()
                        )
        );

        TableColumn<Expense, Double> amountColumn =
                new TableColumn<>("Montant");

        amountColumn.setCellValueFactory(
                data ->
                        new ReadOnlyObjectWrapper<>(
                                data.getValue()
                                        .getAmount()
                        )
        );

        table.getColumns().addAll(
                descriptionColumn,
                amountColumn
        );

        table.setItems(expenses);

        addButton.setOnAction(event -> {

            String description =
                    descriptionField.getText();

            String amountText =
                    amountField.getText();

            try {

                double amount =
                        Double.parseDouble(
                                amountText
                        );

                Expense expense =
                        new Expense(
                                description,
                                amount
                        );

                expenses.add(expense);

                updateTotal();

                descriptionField.clear();
                amountField.clear();

            } catch (Exception e) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setTitle("Erreur");

                alert.setContentText(
                        "Montant invalide"
                );

                alert.showAndWait();
            }
        });

        VBox root = new VBox(
                10,
                descriptionField,
                amountField,
                addButton,
                table,
                totalLabel
        );

        root.setPadding(
                new Insets(15)
        );

        Scene scene =
                new Scene(
                        root,
                        500,
                        450
                );

        stage.setTitle(
                "Expense Tracker"
        );

        stage.setScene(scene);

        stage.show();
    }

    private void updateTotal() {

        double total = 0;

        for (Expense expense : expenses) {

            total += expense.getAmount();
        }

        totalLabel.setText(
                "Total : " + total + " €"
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}