package com.zenith.doro;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.File;

public class TimerElement extends Group {

    private Circle backgroundCircle;
    private Circle foregroundCircle;
    private Circle progressBar;
    private Label clockLabel;
    private Media media = new Media(new File("src/main/resources/alert.mp3").toURI().toString());
    private Media click;
    private MediaPlayer player = new MediaPlayer(media);
    private long seconds;
    private double circumference;
    private boolean isRunning = false;

    private TimerThread runnableThread = new TimerThread(clockLabel, progressBar, this, 0, circumference);

    public TimerElement(Scene scene) {

        int minutes = 25;

        double width = scene.getWidth();
        double height = scene.getHeight();
        System.out.println(height);
        System.out.println(width);

        int circleRadii = (int) (height * 2 / 8);
        this.circumference = 2 * Math.PI * circleRadii;
//        double basis = 450;
        this.backgroundCircle = new Circle();
//        backgroundCircle.setTranslateX(width);
//        backgroundCircle.setTranslateY(height);
        backgroundCircle.setRadius(circleRadii);
        backgroundCircle.setFill(Color.rgb(128, 19, 54));
        backgroundCircle.toFront();


        this.progressBar = new Circle();
        progressBar.setRadius(circleRadii);
//        progressBar.setFill(Color.rgb(200,200,200));
        progressBar.setFill(Color.TRANSPARENT);

        progressBar.setStrokeWidth(5);
        progressBar.setStrokeType(StrokeType.OUTSIDE);
        progressBar.setStrokeLineCap(StrokeLineCap.ROUND);
        progressBar.setMouseTransparent(true);
//        progressBar.setStrokeDashOffset(100);
        progressBar.getStrokeDashArray().addAll(circumference);//
        progressBar.setStyle("-fx-stroke: #C8C8C8;");

        progressBar.toFront();

        this.foregroundCircle = new Circle();
        foregroundCircle.setRadius((int) (circleRadii));
        foregroundCircle.setFill(Color.rgb(128, 19, 54));
        foregroundCircle.toFront();
        foregroundCircle.setOnMouseClicked(event -> start(minutes * 60));
        foregroundCircle.toFront();

        this.clockLabel = new Label("START");
        clockLabel.setTextFill(Color.rgb(200, 200, 200));
        clockLabel.setContentDisplay(ContentDisplay.CENTER);
        clockLabel.setAlignment(Pos.CENTER);
        clockLabel.setTextAlignment(TextAlignment.CENTER);
        clockLabel.setFont(new Font("System", 27));
        clockLabel.toFront();

//
//        this.getChildren().add(backgroundCircle);
        this.getChildren().add(progressBar);
        progressBar.toFront();
        this.getChildren().add(foregroundCircle);
//        this.getChildren().add(clockLabel);

    }

    public Circle getCenter() {
        return this.foregroundCircle;
    }

    public Label getCLabel() {
        return this.clockLabel;
    }

    public void start(long seconds) {
        this.seconds = seconds;

        try{
            System.out.println("isThreadRunning: " + runnableThread.isRunning());
            if(runnableThread.isRunning()){
                System.out.println("Interrupting...");
                runnableThread.interrupt();
            }

            runnableThread = new TimerThread(clockLabel, progressBar, this, seconds, circumference);
            System.out.println(runnableThread);

            runnableThread.start();
            System.out.println("Starting runnableThread");
            System.out.println("[POST] isThreadRunning: " + runnableThread.isRunning());
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void playSound() {
        try {
            player.play();
            player.stop();
        } finally {

        }
    }


}
