package com.example.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URISyntaxException;

/**
 * This class is where the application is launched.
 */

public class Main extends Application
{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws URISyntaxException
    {
        TetrisRenderer renderer = new TetrisRenderer();
        Game newGame = new Game(renderer);
        TetrisController controller = new TetrisController(newGame);
        Scene scene = renderer.getScene();
        stage.setTitle("Tetris");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
