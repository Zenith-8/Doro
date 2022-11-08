package com.zenith.doro;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

public class DoroController {
    @FXML
    private Label welcomeText;

    @FXML
    private StackPane basePane;

    @FXML
    private ImageView tomato;

    @FXML
    private AnchorPane anchorPane;

    private TimerElement timer;

    private Scene scene;

    @FXML
    public void initialize(){
        Animations.setHighlightable(tomato);
    }

    public void postInitialize(){
        tomato.setFitHeight(scene.getHeight()/6);
        tomato.setFitWidth(scene.getWidth()/6);
        timer = new TimerElement(scene);
        basePane.getChildren().add(timer);
        Animations.setHighlightable(timer.getCenter(), 0.75);

        Label clockLabel = timer.getCLabel();
        basePane.getChildren().add(clockLabel);
        clockLabel.setMouseTransparent(true);
        clockLabel.setContentDisplay(ContentDisplay.CENTER);
        clockLabel.setAlignment(Pos.CENTER);
        clockLabel.setTextAlignment(TextAlignment.CENTER);
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }




}