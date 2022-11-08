package com.zenith.doro;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animations {


    public Animations(Node e){}

    public static void setHighlightable(Node e, double endOpacity){

        int duration = 150;

        FadeTransition fadeOut = new FadeTransition();
        FadeTransition fadeIn = new FadeTransition();

        fadeOut.setDuration(Duration.millis(duration));
        fadeOut.setFromValue(1);
        fadeOut.setToValue(endOpacity);
        fadeOut.setNode(e);

        fadeIn.setDuration(Duration.millis(duration));
        fadeIn.setFromValue(endOpacity);
        fadeIn.setToValue(1);
        fadeIn.setNode(e);

        e.setOnMouseEntered(event -> {
            fadeOut.play();
        });

        e.setOnMouseExited(event -> {
            fadeIn.play();
        });

    }

    public static void setHighlightable(Node e){
        setHighlightable(e, 0.4);
    }




}
