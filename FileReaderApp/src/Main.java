import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main extends Application {

    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) {

        textArea = new TextArea();
        textArea.setEditable(false);

        Button openButton = new Button("Open File");

        openButton.setOnAction(e -> openFile(primaryStage));

        BorderPane root = new BorderPane();

        root.setTop(openButton);
        root.setCenter(textArea);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("File Reader App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openFile(Stage stage) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Choose a File");

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {

            try (BufferedReader reader =
                         new BufferedReader(
                                 new FileReader(file))) {

                StringBuilder content =
                        new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null) {

                    content.append(line)
                            .append("\n");
                }

                textArea.setText(content.toString());

            } catch (Exception e) {

                textArea.setText(
                        "Error while reading file."
                );
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}