import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuizApp extends Application {

    private int score = 0;

    @Override
    public void start(Stage stage) {

        Label question =
                new Label("Quel langage utilise Spring Boot ?");

        RadioButton option1 =
                new RadioButton("Python");

        RadioButton option2 =
                new RadioButton("Java");

        RadioButton option3 =
                new RadioButton("PHP");

        RadioButton option4 =
                new RadioButton("C#");

        ToggleGroup group = new ToggleGroup();

        option1.setToggleGroup(group);
        option2.setToggleGroup(group);
        option3.setToggleGroup(group);
        option4.setToggleGroup(group);

        Button submitButton =
                new Button("Valider");

        Label resultLabel =
                new Label("");

        submitButton.setOnAction(e -> {

            RadioButton selected =
                    (RadioButton) group.getSelectedToggle();

            if (selected == null) {

                resultLabel.setText(
                        "Veuillez choisir une réponse."
                );

                return;
            }

            if (selected.getText().equals("Java")) {

                score++;

                resultLabel.setText(
                        "Bonne réponse ! Score : " + score
                );

            } else {

                resultLabel.setText(
                        "Mauvaise réponse."
                );
            }
        });

        VBox root = new VBox(15);

        root.setPadding(new Insets(20));

        root.getChildren().addAll(
                question,
                option1,
                option2,
                option3,
                option4,
                submitButton,
                resultLabel
        );

        Scene scene =
                new Scene(root, 400, 300);

        stage.setTitle("Quiz JavaFX");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}