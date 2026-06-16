import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private StudentController controller =
            new StudentController();

    @Override
    public void start(Stage stage) {

        TextField nameField =
                new TextField();

        nameField.setPromptText("Student Name");

        TextField subjectField =
                new TextField();

        subjectField.setPromptText("Subject");

        TextField gradeField =
                new TextField();

        gradeField.setPromptText("Grade");

        Button addButton =
                new Button("Add Student");

        Button deleteButton =
                new Button("Delete Student");

        Button averageButton =
                new Button("Calculate Average");

        Label averageLabel =
                new Label("Average : 0");

        TableView<Student> table =
                new TableView<>();

        TableColumn<Student, String> nameColumn =
                new TableColumn<>("Name");

        nameColumn.setCellValueFactory(
                data ->
                        new ReadOnlyObjectWrapper<>(
                                data.getValue().getName()
                        )
        );

        TableColumn<Student, String> subjectColumn =
                new TableColumn<>("Subject");

        subjectColumn.setCellValueFactory(
                data ->
                        new ReadOnlyObjectWrapper<>(
                                data.getValue().getSubject()
                        )
        );

        TableColumn<Student, Double> gradeColumn =
                new TableColumn<>("Grade");

        gradeColumn.setCellValueFactory(
                data ->
                        new ReadOnlyObjectWrapper<>(
                                data.getValue().getGrade()
                        )
        );

        table.getColumns().addAll(
                nameColumn,
                subjectColumn,
                gradeColumn
        );

        table.setItems(
                controller.getStudents()
        );

        addButton.setOnAction(e -> {

            try {

                String name =
                        nameField.getText();

                String subject =
                        subjectField.getText();

                double grade =
                        Double.parseDouble(
                                gradeField.getText()
                        );

                controller.addStudent(
                        new Student(
                                name,
                                subject,
                                grade
                        )
                );

                nameField.clear();
                subjectField.clear();
                gradeField.clear();

            } catch (Exception ex) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setContentText(
                        "Invalid data"
                );

                alert.show();
            }
        });

        deleteButton.setOnAction(e -> {

            Student selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {
                controller.deleteStudent(
                        selected
                );
            }
        });

        averageButton.setOnAction(e -> {

            double avg =
                    controller.calculateAverage();

            averageLabel.setText(
                    "Average : "
                            + String.format("%.2f", avg)
            );
        });

        VBox root =
                new VBox(
                        10,
                        nameField,
                        subjectField,
                        gradeField,
                        addButton,
                        deleteButton,
                        averageButton,
                        averageLabel,
                        table
                );

        root.setPadding(
                new Insets(15)
        );

        Scene scene =
                new Scene(
                        root,
                        700,
                        500
                );

        stage.setTitle(
                "Student Grade Manager"
        );

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}