import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class PasswordGeneratorApp extends Application {

    private static final String LOWERCASE =
            "abcdefghijklmnopqrstuvwxyz";

    private static final String UPPERCASE =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String NUMBERS =
            "0123456789";

    private static final String SYMBOLS =
            "!@#$%^&*()_-+=<>?/";

    @Override
    public void start(Stage stage) {

        Label title =
                new Label("Password Generator");

        TextField lengthField =
                new TextField();

        lengthField.setPromptText("Password Length");

        CheckBox uppercaseCheck =
                new CheckBox("Include Uppercase");

        CheckBox numbersCheck =
                new CheckBox("Include Numbers");

        CheckBox symbolsCheck =
                new CheckBox("Include Symbols");

        TextArea resultArea =
                new TextArea();

        resultArea.setEditable(false);

        Button generateButton =
                new Button("Generate Password");

        generateButton.setOnAction(e -> {

            try {

                int length =
                        Integer.parseInt(lengthField.getText());

                String password =
                        generatePassword(
                                length,
                                uppercaseCheck.isSelected(),
                                numbersCheck.isSelected(),
                                symbolsCheck.isSelected()
                        );

                resultArea.setText(password);

            } catch (NumberFormatException ex) {

                resultArea.setText(
                        "Please enter a valid number."
                );
            }
        });

        VBox root = new VBox(10);

        root.setPadding(new Insets(20));

        root.getChildren().addAll(
                title,
                lengthField,
                uppercaseCheck,
                numbersCheck,
                symbolsCheck,
                generateButton,
                resultArea
        );

        Scene scene =
                new Scene(root, 400, 350);

        stage.setTitle("Password Generator");

        stage.setScene(scene);

        stage.show();
    }

    private String generatePassword(
            int length,
            boolean uppercase,
            boolean numbers,
            boolean symbols
    ) {

        String characters = LOWERCASE;

        if (uppercase) {
            characters += UPPERCASE;
        }

        if (numbers) {
            characters += NUMBERS;
        }

        if (symbols) {
            characters += SYMBOLS;
        }

        Random random = new Random();

        StringBuilder password =
                new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index =
                    random.nextInt(characters.length());

            password.append(
                    characters.charAt(index)
            );
        }

        return password.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}