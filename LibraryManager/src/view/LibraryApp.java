package view;

import controller.LibraryController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryApp extends Application {

    @Override
    public void start(Stage stage) {

        LibraryController controller =
                new LibraryController();

        Label titleLabel =
                new Label("Library Manager");

        TextField titleField =
                new TextField();

        titleField.setPromptText("Book Title");

        TextField authorField =
                new TextField();

        authorField.setPromptText("Author");

        Button addButton =
                new Button("Add Book");

        ListView listView =
                new ListView();

        TextField searchField =
                new TextField();

        searchField.setPromptText("Search book");

        Button searchButton =
                new Button("Search");

        addButton.setOnAction(e -> {

            controller.addBook(
                    titleField.getText(),
                    authorField.getText()
            );

            listView.setItems(
                    controller.getBooks()
            );

            titleField.clear();
            authorField.clear();
        });

        searchButton.setOnAction(e -> {

            listView.setItems(
                    controller.searchBook(
                            searchField.getText()
                    )
            );
        });

        VBox root = new VBox(
                10,
                titleLabel,
                titleField,
                authorField,
                addButton,
                searchField,
                searchButton,
                listView
        );

        root.setPadding(new Insets(15));

        Scene scene =
                new Scene(root, 400, 500);

        stage.setScene(scene);
        stage.setTitle("Library Manager");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}