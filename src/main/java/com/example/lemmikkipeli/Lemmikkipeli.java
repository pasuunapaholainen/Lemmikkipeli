package com.example.lemmikkipeli;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Lemmikkipeli extends Application {
    ArrayList<Lemmikki> lemmikkilista = new ArrayList<Lemmikki>();
    ArrayList<String> nimilista = new ArrayList<String>();
    Button leikiNappi = new Button("Leiki lemmikin kanssa");
    Button ruokiNappi = new Button("Ruoki lemmikkiä");
    Button uusiLemmikki = new Button("Luo uusi lemmikki");


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
        ylaboksi.setAlignment(Pos.CENTER);
        ylaboksi.setPadding(new Insets(10,0,10,0));

        HBox alaboksi = new HBox();
        alaboksi.getChildren().add(leikiNappi);
        alaboksi.getChildren().add(ruokiNappi);
        alaboksi.getChildren().add(uusiLemmikki);
        alaboksi.setAlignment(Pos.CENTER);
        alaboksi.setSpacing(50);
        alaboksi.setPadding(new Insets(10, 0, 10, 0));


        BorderPane pohja = new BorderPane();
        pohja.setTop(ylaboksi);
        pohja.setBottom(alaboksi);

        Scene kehys = new Scene(pohja, 600, 450);
        ikkuna.setScene(kehys);
        ikkuna.setTitle("Lemmikkipeli");
        ikkuna.show();
    }
}
