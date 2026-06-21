import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private int reservedSeats = 0;

    private Label reservedLabel;
    private Label availableLabel;

    private final int ROWS = 5;
    private final int COLS = 5;

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        reservedLabel = new Label("Reserved: 0");
        availableLabel = new Label("Available: 25");

        VBox infoBox = new VBox(10,
                reservedLabel,
                availableLabel
        );

        infoBox.setAlignment(Pos.CENTER);
        infoBox.setPadding(new Insets(20));

        createSeats(grid);

        root.setCenter(grid);
        root.setBottom(infoBox);

        Scene scene = new Scene(root, 500, 500);

        stage.setTitle("Seat Reservation System");
        stage.setScene(scene);
        stage.show();
    }

    private void createSeats(GridPane grid) {

        for (int row = 0; row < ROWS; row++) {

            for (int col = 0; col < COLS; col++) {

                Button seat = new Button();

                seat.setText((row + 1) + "-" + (col + 1));

                seat.setPrefWidth(80);
                seat.setPrefHeight(50);

                seat.setStyle(
                        "-fx-background-color: lightgreen;"
                );

                seat.setOnAction(event -> {

                    if (seat.getStyle().contains("lightgreen")) {

                        seat.setStyle(
                                "-fx-background-color: tomato;"
                        );

                        reservedSeats++;

                    } else {

                        seat.setStyle(
                                "-fx-background-color: lightgreen;"
                        );

                        reservedSeats--;
                    }

                    updateLabels();
                });

                grid.add(seat, col, row);
            }
        }
    }

    private void updateLabels() {

        reservedLabel.setText(
                "Reserved: " + reservedSeats
        );

        availableLabel.setText(
                "Available: " +
                        ((ROWS * COLS) - reservedSeats)
        );
    }

    public static void main(String[] args) {
        launch();
    }
}