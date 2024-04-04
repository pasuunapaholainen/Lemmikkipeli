package com.example.lemmikkipeli;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
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
    TextField tfNimi = new TextField();
    //String[] lajit = {"Koira", "Kissa", "Marsu"};
    //ComboBox<String> lajiValinta = new ComboBox<>(FXCollections.observableArrayList(lajit));
    RadioButton koira = new RadioButton("Koira");
    RadioButton kissa = new RadioButton("Kissa");
    RadioButton marsu = new RadioButton("Marsu");
    Button luo = new Button("Luo");
    boolean onJoNimi;
    Button ponnahdusNappi = new Button("Ok");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ikkuna) {
        // debuggaukseen
        //lemmikkilista.add(new Lemmikki("Koira","Musti"));
        HBox ylaboksi = new HBox();
        ylaboksi.setSpacing(10);
        ylaboksi.getChildren().add(new Label("Valitse lemmikki:"));
        ComboBox<String> lemmikkiValinta = new ComboBox<>();
        if(!lemmikkilista.isEmpty()) {
            for (int i = 0; i < lemmikkilista.size(); i++) {
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
        ylaboksi.setStyle("-fx-border-color: gray");

        HBox nimirivi = new HBox();
        nimirivi.getChildren().add(new Label("Lemmikin nimi: "));
        nimirivi.getChildren().add(tfNimi);
        tfNimi.setMaxWidth(100);
        nimirivi.setAlignment(Pos.CENTER);
        HBox lajirivi = new HBox();
        lajirivi.getChildren().add(new Label("Lemmikin laji: "));
        //lajirivi.getChildren().add(lajiValinta);
        VBox lemmikit = new VBox();
        lemmikit.getChildren().add(koira);
        lemmikit.getChildren().add(kissa);
        lemmikit.getChildren().add(marsu);
        lajirivi.getChildren().add(lemmikit);
        lajirivi.setAlignment(Pos.CENTER);
        VBox lisaysIkkuna = new VBox();
        lisaysIkkuna.getChildren().add(nimirivi);
        lisaysIkkuna.getChildren().add(lajirivi);
        lisaysIkkuna.setAlignment(Pos.CENTER);
        lisaysIkkuna.getChildren().add(luo);
        lisaysIkkuna.setSpacing(10);

        HBox alaboksi = new HBox();
        alaboksi.getChildren().add(leikiNappi);
        alaboksi.getChildren().add(ruokiNappi);
        alaboksi.getChildren().add(uusiLemmikki);
        alaboksi.setAlignment(Pos.CENTER);
        alaboksi.setSpacing(50);
        alaboksi.setPadding(new Insets(10, 0, 10, 0));
        alaboksi.setStyle("-fx-border-color: gray");


        BorderPane pohja = new BorderPane();
        pohja.setTop(ylaboksi);
        pohja.setBottom(alaboksi);
        //pohja.setCenter(lisaysIkkuna);

        Scene kehys = new Scene(pohja, 600, 450);
        ikkuna.setScene(kehys);
        ikkuna.setTitle("Lemmikkipeli");
        ikkuna.show();

        ToggleGroup lemmikkiGroup = new ToggleGroup();
        koira.setToggleGroup(lemmikkiGroup);
        kissa.setToggleGroup(lemmikkiGroup);
        marsu.setToggleGroup(lemmikkiGroup);

        uusiLemmikki.setOnAction(e -> {
            pohja.setCenter(lisaysIkkuna);
        });
        String[] laji = {null};
        koira.setOnAction(ev -> {
            laji[0] = "Koira";
        });
        kissa.setOnAction(ev -> {
            laji[0] = "Kissa";
        });
        marsu.setOnAction(ev -> {
            laji[0] = "Marsu";
        });

        VBox ponnahdusPohja = new VBox();
        ponnahdusPohja.getChildren().add(new Label("Lemmikeillä pitää olla eri nimet!"));
        ponnahdusPohja.getChildren().add(ponnahdusNappi);
        ponnahdusPohja.setAlignment(Pos.CENTER);
        ponnahdusPohja.setSpacing(20);
        Stage ponnnahdus = new Stage();
        Scene ponnahdusKehys = new Scene(ponnahdusPohja, 200, 100);
        ponnnahdus.setScene(ponnahdusKehys);
        ponnahdusNappi.setOnAction(e -> {
            ponnnahdus.close();
        });

        luo.setOnAction(e -> {
            onJoNimi = false;
            for(int i = 0; i<lemmikkilista.size();i++){
                if(lemmikkilista.get(i).getNimi().equals(tfNimi.getText())){
                    onJoNimi = true;
                }
            }
            if(onJoNimi){
                ponnnahdus.show();
            }
            if(tfNimi.getText() != null && laji[0] != null && !onJoNimi){

                String nimi = tfNimi.getText();
                System.out.println(laji[0]);
                lemmikkilista.add(new Lemmikki(laji[0], nimi));
                nimilista.add(nimi);
                lemmikkiValinta.setItems(null);
                lemmikkiValinta.setItems(FXCollections.observableArrayList(nimilista));

                // debuggaukseen
                System.out.println(lemmikkilista.getLast().toString());
            }
        });

        lemmikkiValinta.setOnAction(e -> {
            if(!lemmikkiValinta.getValue().equals("Ei lemmikkejä")) {
                String nimi = lemmikkiValinta.getValue();
                //System.out.println(nimi);
                Lemmikki valittu = null;
                for (int i = 0; i < lemmikkilista.size(); i++) {
                    if (lemmikkilista.get(i).getNimi().equals(nimi)) {
                        valittu = lemmikkilista.get(i);
                    }
                }

                //TODO
                // Väliaikainen muotoilu, loppuversioon kuvat

                VBox lemmikkiIkkuna = new VBox();
                lemmikkiIkkuna.getChildren().add(new Label(valittu.getNimi()));
                lemmikkiIkkuna.getChildren().add(new Label(valittu.getLaji()));
                lemmikkiIkkuna.setAlignment(Pos.CENTER);
                pohja.setCenter(lemmikkiIkkuna);
            }

        });
        //TODO
        // Toiminnallisuudet leikkimiselle ja ruokinnalle
        // Tiedostoon tallentaminen
    }
}
