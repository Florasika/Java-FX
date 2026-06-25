import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    private TextArea textArea;

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        textArea = new TextArea();

        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");

        MenuItem newFile = new MenuItem("New");
        MenuItem openFile = new MenuItem("Open");
        MenuItem saveFile = new MenuItem("Save");
        MenuItem exit = new MenuItem("Exit");

        fileMenu.getItems().addAll(
                newFile,
                openFile,
                saveFile,
                new SeparatorMenuItem(),
                exit
        );

        menuBar.getMenus().add(fileMenu);

        root.setTop(menuBar);
        root.setCenter(textArea);

        newFile.setOnAction(e -> {
            textArea.clear();
        });

        openFile.setOnAction(e -> openFile(stage));

        saveFile.setOnAction(e -> saveFile(stage));

        exit.setOnAction(e -> stage.close());

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("NotepadFX");
        stage.setScene(scene);
        stage.show();
    }

    private void openFile(Stage stage) {

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {

            try (BufferedReader reader =
                         new BufferedReader(
                                 new FileReader(file))) {

                String line;

                textArea.clear();

                while ((line = reader.readLine()) != null) {
                    textArea.appendText(line + "\n");
                }

            } catch (IOException e) {

                showAlert(
                        "Error",
                        "Unable to open file."
                );
            }
        }
    }

    private void saveFile(Stage stage) {

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {

            try (BufferedWriter writer =
                         new BufferedWriter(
                                 new FileWriter(file))) {

                writer.write(textArea.getText());

            } catch (IOException e) {

                showAlert(
                        "Error",
                        "Unable to save file."
                );
            }
        }
    }

    private void showAlert(
            String title,
            String message) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}