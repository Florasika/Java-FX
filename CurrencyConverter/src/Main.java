import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Currency Converter");

        TextField input = new TextField();
        input.setPromptText("Enter amount");

        ComboBox<String> currencyBox = new ComboBox<>();
        currencyBox.getItems().addAll(
                "EUR -> USD",
                "USD -> EUR",
                "FCFA -> EUR"
        );
        currencyBox.setValue("EUR -> USD");

        Button convertBtn = new Button("Convert");

        Label result = new Label();

        convertBtn.setOnAction(e -> {

            try {
                double amount = Double.parseDouble(input.getText());
                String type = currencyBox.getValue();
                double converted = 0;

                switch (type) {
                    case "EUR -> USD":
                        converted = amount * 1.09;
                        break;

                    case "USD -> EUR":
                        converted = amount * 0.92;
                        break;

                    case "FCFA -> EUR":
                        converted = amount / 655.96;
                        break;
                }

                result.setText("Result: " + String.format("%.2f", converted));

            } catch (Exception ex) {
                result.setText("Invalid input");
            }
        });

        VBox root = new VBox(10, title, input, currencyBox, convertBtn, result);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 300, 250);

        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}