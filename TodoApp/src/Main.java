import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private ObservableList<String> tasks =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        Label title = new Label("ToDo List");

        TextField taskField = new TextField();
        taskField.setPromptText("Enter a task");

        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button completeButton = new Button("Complete");

        ListView<String> listView =
                new ListView<>(tasks);

        addButton.setOnAction(e -> {

            String task = taskField.getText();

            if (!task.isEmpty()) {
                tasks.add(task);
                taskField.clear();
            }
        });

        deleteButton.setOnAction(e -> {

            String selected =
                    listView.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {
                tasks.remove(selected);
            }
        });

        completeButton.setOnAction(e -> {

            int index =
                    listView.getSelectionModel()
                            .getSelectedIndex();

            if (index >= 0) {

                String task = tasks.get(index);

                if (!task.startsWith("✓ ")) {
                    tasks.set(index, "✓ " + task);
                }
            }
        });

        HBox buttons = new HBox(
                10,
                addButton,
                completeButton,
                deleteButton
        );

        VBox root = new VBox(
                10,
                title,
                taskField,
                buttons,
                listView
        );

        root.setPadding(new Insets(15));

        Scene scene =
                new Scene(root, 400, 500);

        stage.setTitle("ToDo App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}