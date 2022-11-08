package com.zenith.doro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class DoroMain extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        final double[] xOffset = {0};
        final double[] yOffset = {0};

        int size1 = 617/2;
        int size2 = 526/2;

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("doro.fxml")));
        Parent root = loader.load();

        root.setOnMousePressed(event -> {
            xOffset[0] = event.getSceneX();
            yOffset[0] = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset[0]);
            stage.setY(event.getScreenY() - yOffset[0]);
        });
        Scene scene = new Scene(root, size1, size2);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        stage.setScene(scene);
        DoroController controller = loader.getController();
        controller.setScene(scene);
        controller.postInitialize();

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setResizable(true);

        stage.show();

    }
}