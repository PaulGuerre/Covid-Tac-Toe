package com.example.test;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game {

    private GridPane grid;
    private int tour = 0;

    /**
     * Constructor
     * @param gp
     */
    public Game(GridPane gp) {
        this.grid = gp;

        for(int i = 0; i < this.grid.getChildren().size(); i ++) {
            Pane p = (Pane) this.grid.getChildren().get(i);
            p.getChildren().clear();
            p.setStyle("-fx-background-color: LIGHTBLUE; -fx-border-color: black");
        }

        ticTacToe();
    }

    /**
     * Check if there is a winner
     * @return
     */
    public String isWin() {
        String winnerName = "";

        if((this.grid.getChildren().get(0).getId() == "S" && this.grid.getChildren().get(1).getId() == "S" && this.grid.getChildren().get(2).getId() == "S") ||
                (this.grid.getChildren().get(3).getId() == "S" && this.grid.getChildren().get(4).getId() == "S" && this.grid.getChildren().get(5).getId() == "S") ||
                (this.grid.getChildren().get(6).getId() == "S" && this.grid.getChildren().get(7).getId() == "S" && this.grid.getChildren().get(8).getId() == "S") ||
                (this.grid.getChildren().get(0).getId() == "S" && this.grid.getChildren().get(3).getId() == "S" && this.grid.getChildren().get(6).getId() == "S") ||
                (this.grid.getChildren().get(1).getId() == "S" && this.grid.getChildren().get(4).getId() == "S" && this.grid.getChildren().get(7).getId() == "S") ||
                (this.grid.getChildren().get(2).getId() == "S" && this.grid.getChildren().get(5).getId() == "S" && this.grid.getChildren().get(8).getId() == "S") ||
                (this.grid.getChildren().get(0).getId() == "S" && this.grid.getChildren().get(4).getId() == "S" && this.grid.getChildren().get(8).getId() == "S") ||
                (this.grid.getChildren().get(2).getId() == "S" && this.grid.getChildren().get(4).getId() == "S" && this.grid.getChildren().get(6).getId() == "S") ) {
            winnerName = "Vaccination";
        }
        else if((this.grid.getChildren().get(0).getId() == "C" && this.grid.getChildren().get(1).getId() == "C" && this.grid.getChildren().get(2).getId() == "C") ||
                (this.grid.getChildren().get(3).getId() == "C" && this.grid.getChildren().get(4).getId() == "C" && this.grid.getChildren().get(5).getId() == "C") ||
                (this.grid.getChildren().get(6).getId() == "C" && this.grid.getChildren().get(7).getId() == "C" && this.grid.getChildren().get(8).getId() == "C") ||
                (this.grid.getChildren().get(0).getId() == "C" && this.grid.getChildren().get(3).getId() == "C" && this.grid.getChildren().get(6).getId() == "C") ||
                (this.grid.getChildren().get(1).getId() == "C" && this.grid.getChildren().get(4).getId() == "C" && this.grid.getChildren().get(7).getId() == "C") ||
                (this.grid.getChildren().get(2).getId() == "C" && this.grid.getChildren().get(5).getId() == "C" && this.grid.getChildren().get(8).getId() == "C") ||
                (this.grid.getChildren().get(0).getId() == "C" && this.grid.getChildren().get(4).getId() == "C" && this.grid.getChildren().get(8).getId() == "C") ||
                (this.grid.getChildren().get(2).getId() == "C" && this.grid.getChildren().get(4).getId() == "C" && this.grid.getChildren().get(6).getId() == "C")) {
            winnerName = "Covid";
        }
        else {
            int nb = 0;

            for(int i = 0; i < this.grid.getChildren().size(); i ++) {
                if(this.grid.getChildren().get(i).getId() == "S" || this.grid.getChildren().get(i).getId() == "C") {
                    nb ++;
                }
            }

            if(nb == this.grid.getChildren().size()) {
                winnerName = "Nobody";
            }
        }

        return winnerName;
    }

    /**
     * Game behavior
     */
    public void ticTacToe() {
        this.grid.setOnMouseClicked(e -> {
            if(((Pane) e.getTarget()).getId() != "S" && ((Pane) e.getTarget()).getId() != "C") {
                Pane p = (Pane) e.getTarget();

                if(this.tour % 2 == 0) {
                    setSeringue(p);
                }
                else if(this.tour % 2 != 0) {
                    setCovid(p);
                }

                if(isWin().equals("Vaccination")) {
                    showWinner("Vaccination");
                }
                else if(isWin().equals("Covid")) {
                    showWinner("Covid");
                }
                else if(isWin().equals("Nobody")) {
                    showWinner("Nobody");
                }

                this.tour ++;
            }

            e.consume();
        });
    }

    /**
     * Set seringue png
     * @param p
     */
    public void setSeringue(Pane p) {
        ImageView iv = new ImageView("file:/home/paul/IdeaProjects/CovidTacToe/src/main/java/com/example/test/resources/seringue.png");

        iv.setX(-25);
        iv.setY(0);

        p.getChildren().add(iv);
        p.setId("S");
    }

    /**
     * Set covid png
     * @param p
     */
    public void setCovid(Pane p) {
        ImageView iv = new ImageView("file:/home/paul/IdeaProjects/CovidTacToe/src/main/java/com/example/test/resources/covid.png");

        iv.setX(25);
        iv.setY(25);

        p.getChildren().add(iv);
        p.setId("C");
    }

    /**
     * Show the winner
     * @param winnerName
     */
    public void showWinner(String winnerName) {
        Text t = new Text();

        if(winnerName == "Covid") {
            t.setText(winnerName + " won !");
            t.setStyle("-fx-font-size: 250%;");
        }
        else if(winnerName == "Nobody") {
            t.setText(winnerName + "\nwon...");
            t.setStyle("-fx-font-size: 250%; -fx-text-alignment: center");
            t.setX(40);
        }
        else {
            t.setText(winnerName + "\nwon !");
            t.setStyle("-fx-font-size: 250%; -fx-text-alignment: center");
            t.setX(5);
        }
        t.setY(100);

        this.grid.setOnMouseClicked(null);
        for(int i = 0; i < this.grid.getChildren().size(); i ++) {
            Pane p = (Pane) this.grid.getChildren().get(i);
            p.getChildren().clear();
            p.setStyle("-fx-background-color: LIGHTBLUE;");
        }

        Pane pText = (Pane) this.grid.getChildren().get(3);

        pText.getChildren().add(t);

        Button reset = new Button("Restart");
        reset.setStyle("-fx-background-color: white; -fx-font-size: 250%");
        reset.setLayoutX(25);


        reset.setOnMouseClicked(e -> {
            HelloApplication app = new HelloApplication();
            app.start(new Stage());
        });

        Pane pReset = (Pane) this.grid.getChildren().get(4);

        pReset.getChildren().add(reset);

        Button quit = new Button("Exit Game");
        quit.setStyle("-fx-background-color: white; -fx-font-size: 200%");
        quit.setLayoutX(20);

        quit.setOnMouseClicked(e -> {
            System.exit(0);
        });

        Pane pQuit = (Pane) this.grid.getChildren().get(5);

        pQuit.getChildren().add(quit);
    }
}
