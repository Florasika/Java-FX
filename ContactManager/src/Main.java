import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final ObservableList<Contact> contacts =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        Label title = new Label("Gestionnaire de Contacts");

        TextField nomField = new TextField();
        nomField.setPromptText("Nom");

        TextField telephoneField = new TextField();
        telephoneField.setPromptText("Téléphone");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        Button addButton = new Button("Ajouter");
        Button deleteButton = new Button("Supprimer");

        TableView<Contact> table = new TableView<>();

        TableColumn<Contact, String> nomCol =
                new TableColumn<>("Nom");

        nomCol.setCellValueFactory(
                new PropertyValueFactory<>("nom")
        );

        TableColumn<Contact, String> telCol =
                new TableColumn<>("Téléphone");

        telCol.setCellValueFactory(
                new PropertyValueFactory<>("telephone")
        );

        TableColumn<Contact, String> emailCol =
                new TableColumn<>("Email");

        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        table.getColumns().addAll(
                nomCol,
                telCol,
                emailCol
        );

        table.setItems(contacts);

        addButton.setOnAction(e -> {

            String nom = nomField.getText();
            String telephone = telephoneField.getText();
            String email = emailField.getText();

            if (!nom.isEmpty()
                    && !telephone.isEmpty()
                    && !email.isEmpty()) {

                contacts.add(
                        new Contact(
                                nom,
                                telephone,
                                email
                        )
                );

                nomField.clear();
                telephoneField.clear();
                emailField.clear();
            }
        });

        deleteButton.setOnAction(e -> {

            Contact selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {
                contacts.remove(selected);
            }
        });

        VBox root = new VBox(
                10,
                title,
                nomField,
                telephoneField,
                emailField,
                addButton,
                deleteButton,
                table
        );

        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 700, 500);

        stage.setTitle("Contact Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}