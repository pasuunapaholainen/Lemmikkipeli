package com.example.lemmikkipeli;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Lemmikkipeli extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ikkuna) {
        GridPane pohja = new GridPane();

        Scene kehys = new Scene(pohja, 600, 450);
        ikkuna.setScene(kehys);
        ikkuna.setTitle("Lemmikkipeli");
        ikkuna.show();
    }
}
