import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private String operator = "";
    private double firstNumber = 0;
    private boolean newNumber = true;

    @Override
    public void start(Stage stage) {

        TextField display = new TextField();
        display.setEditable(false);
        display.setPrefHeight(60);

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        root.add(display, 0, 0, 4, 1);

        String[][] buttons = {
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"0", "C", "=", "+"}
        };

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {

                String text = buttons[row][col];
                Button button = new Button(text);
                button.setPrefSize(80, 60);

                button.setOnAction(e -> {

                    if ("0123456789".contains(text)) {

                        if (newNumber) {
                            display.setText(text);
                            newNumber = false;
                        } else {
                            display.setText(display.getText() + text);
                        }

                    } else if ("+-*/".contains(text)) {

                        firstNumber = Double.parseDouble(display.getText());
                        operator = text;
                        newNumber = true;

                    } else if (text.equals("=")) {

                        double secondNumber = Double.parseDouble(display.getText());
                        double result = 0;

                        switch (operator) {
                            case "+" -> result = firstNumber + secondNumber;
                            case "-" -> result = firstNumber - secondNumber;
                            case "*" -> result = firstNumber * secondNumber;
                            case "/" -> result = firstNumber / secondNumber;
                        }

                        display.setText(String.valueOf(result));
                        newNumber = true;

                    } else if (text.equals("C")) {

                        display.clear();
                        firstNumber = 0;
                        operator = "";
                        newNumber = true;
                    }
                });

                root.add(button, col, row + 1);
            }
        }

        Scene scene = new Scene(root, 350, 400);

        stage.setTitle("CalculatorFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}