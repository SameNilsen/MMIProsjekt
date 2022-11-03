package test;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ExampleProjectController implements Initializable {
    @FXML
    private TextField firstNumber, secondNumber, operator;

    @FXML
    private AnchorPane background;

    @FXML
    private Label feil_label;

    @FXML
    private Label time_label;

    @FXML
    private Label clicks_label;

    @FXML
    private Label stop_label;

    @FXML
    private Label mousePos;

    private int feil = 0;

    private Long startTime;

    private boolean start = false;

    private boolean rand = true;
    private int nonRandI = 0;
    private Integer[] listX = new Integer[]{835, 410, 961, 118, 968, 621, 258, 1041, 585, 1150};
    private Integer[] listY = new Integer[]{192, 40, 136, 296, 127, 522, 205, 521, 547, 241};
    private int width;
    private int heigth;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private int clicks = -1;

    @FXML
    private Button button;

    @FXML
    private Button restartButton;

    @FXML
    private Button randomButton;

    Random random = new Random();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        background.setStyle("-fx-border-color: red;");
        background.setPrefWidth(1279);
        background.setPrefHeight(696);
        button.setLayoutX(550);
        button.setLayoutY(270);
        button.setText("Press to start");
        stop_label.setVisible(false);
        stop_label.setLayoutX(500);
        stop_label.setLayoutY(200);
        // startTime = System.nanoTime();
    }

    AnimationTimer timerforTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            { 
                double time = (currentNanoTime-startTime)*Math.pow(10, -9);
                if (start == false){
                    if (time > 3){
                        stop_label.setVisible(false);
                        timerforTimer.stop();
                    }
                }
                else{
                    time_label.setText("Time: " + df.format(time));
                }
            }
        };
    
    public void restart() {
        clicks = -1;
        clicks_label.setText("Clicks: " + clicks);
        feil = 0;
        feil_label.setText("Antall feil: " + feil);
    }

    @FXML
    private void handleButtonClick() {
        if (start == false){
            startTime = System.nanoTime();
            timerforTimer.start();
            start = true;
            nonRandI = 0;
            restart();
            button.setText("Trykk");            
        }
        if (clicks >= 9){
            button.setLayoutX(550);
            button.setLayoutY(270);
            button.setText("Press to start");
            clicks++;
            clicks_label.setText("Clicks: " + clicks);
            timerforTimer.stop();
            System.out.println("Ferdig! " + feil_label.getText() +", "+ time_label.getText() +", "+ clicks_label.getText());
            start = false;
            stop_label.setVisible(true);
            startTime = System.nanoTime();
            timerforTimer.start();
        }
        else{
            if (rand){
                width = random.nextInt(1279-103);
                heigth = random.nextInt(696-103);
            }
            else{
                width = listX[nonRandI];
                heigth = listY[nonRandI];
                nonRandI++;
            }
            button.setLayoutX(width);
            button.setLayoutY(heigth);
            clicks++;
            clicks_label.setText("Clicks: " + clicks);
            // System.out.println(width);
            // System.out.println(heigth);
        }
    }

    @FXML
    private void handleMissClick() {
        if (start){
            feil++;
            feil_label.setText("Antall feil: " + String.valueOf(feil));
        }
    }

    @FXML
    private void handleResetButton() {
        restart();
        button.setLayoutX(550);
        button.setLayoutY(270);
        button.setText("Press to start");
        timerforTimer.stop();
        time_label.setText("Time: 0");
        clicks_label.setText("Clicks: 0");
        start = false;
    }

    @FXML
    private void handleRandomButton() {
        if (rand){rand = false;}
        else if (rand == false){rand = true;}
        randomButton.setText("Random: " + String.valueOf(rand));
    }

    @FXML
    private void moveScreen(MouseEvent e) {
        mousePos.setText("x=" + String.valueOf(Math.round(e.getX())) + " : y=" + String.valueOf(Math.round(e.getY())));
    }

}
