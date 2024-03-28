package com.example.lemmikkipeli;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Lemmikkipeli extends Application {
    ArrayList<Lemmikki> lemmikkilista = new ArrayList<Lemmikki>();
    ArrayList<String> nimilista = new ArrayList<String>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ikkuna) {
        HBox ylaboksi = new HBox();
        ylaboksi.setSpacing(10);
        ylaboksi.getChildren().add(new Label("Valitse lemmikki:"));
        ComboBox<String> lemmikkiValinta = new ComboBox<>();
        if(!lemmikkilista.isEmpty()) {
            for (int i = 0; i <= lemmikkilista.size(); i++) {
                Lemmikki lemmikki = lemmikkilista.get(i);
                nimilista.add(lemmikki.getNimi());
            }
            lemmikkiValinta.setItems(FXCollections.observableArrayList(nimilista));
        }else {
            String[] tyhjaLista = {"Ei lemmikkejä"};
            lemmikkiValinta.setItems(FXCollections.observableArrayList(tyhjaLista));
        }
        ylaboksi.getChildren().add(lemmikkiValinta);


        GridPane pohja = new GridPane();
        pohja.getChildren().add(ylaboksi);

        Scene kehys = new Scene(pohja, 600, 450);
        ikkuna.setScene(kehys);
        ikkuna.setTitle("Lemmikkipeli");
        ikkuna.show();
    }
}
