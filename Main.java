package com.example.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();

        //Create 9 panes
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < 3; j ++) {
                Pane p = new Pane();

                p.setStyle("-fx-background-color: LIGHTBLUE;");
                p.setPrefSize(200,200);
                p.setId(i + "//" + j);

                if(i == 1 && j == 1) {
                    Text t = new Text("Covid Tac Toe");
                    t.setStyle("-fx-font-size: 250%");
                    t.setY(75);
                    t.setX(-30);
                    p.getChildren().add(t);
                }
                else if(i == 1 && j == 2) {
                    Button b = new Button("Play");
                    b.setStyle("-fx-background-color: white; -fx-font-size: 200%");
                    b.setLayoutX(50);
                    p.getChildren().add(b);

                    b.setOnMouseClicked(e -> {
                        Game g = new Game(gridPane);
                    });
                }

                gridPane.add(p, i, j);
            }
        }

        Scene scene = new Scene(gridPane);

        stage.setTitle("Covid Tac Toe");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
