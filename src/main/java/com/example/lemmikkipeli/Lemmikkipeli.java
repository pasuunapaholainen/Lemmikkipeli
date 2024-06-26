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
import java.io.*;
import java.util.ArrayList;

//TODO
// Javadoc
// Kuvat lemmikeille

/**
 * Lemmikkipeli, jossa lemmikkejä pitää ruokkia ja niiden kanssa pitää leikkiä tietyin väliajoin.
 * Itse ohjelmakoodista löytyy lisää kommentteja.
 *
 * @author Olli Tirkkonen
 */
public class Lemmikkipeli extends Application {
    private ArrayList<Lemmikki> lemmikkilista = new ArrayList<Lemmikki>();
    private ArrayList<String> nimilista = new ArrayList<String>();
    private final String tiedostoNimi = "lemmikit.dat";
    private final Button leikiNappi = new Button("Leiki lemmikin kanssa");
    private final Button ruokiNappi = new Button("Ruoki lemmikkiä");
    private final Button uusiLemmikki = new Button("Luo uusi lemmikki");
    private TextField tfNimi = new TextField();
    private final RadioButton koira = new RadioButton("Koira");
    private final RadioButton kissa = new RadioButton("Kissa");
    private final RadioButton marsu = new RadioButton("Marsu");
    private final Button luo = new Button("Luo");
    private boolean onJoNimi;
    private final Button ponnahdusNappi = new Button("Ok");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ikkuna) {
        /**
         * Tässä kohdassa avataan mahdollisesti olemassa oleva tiedosta ja kirjoitetaan siinä olevat oliot välimuistiin.
         */
        File lemmikkiTiedosto = new File(tiedostoNimi);
        if(lemmikkiTiedosto.exists()){
            ObjectInputStream lTiedosto = null;
            ArrayList<Lemmikki> lemmikitTiedosto = null;
            try{
                lTiedosto = new ObjectInputStream(new FileInputStream(tiedostoNimi));
                lemmikitTiedosto = (ArrayList<Lemmikki>) lTiedosto.readObject();
            } catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            } finally {
                if(!lemmikitTiedosto.isEmpty()){
                    lemmikkilista.addAll(lemmikitTiedosto);
                }
                try {
                    lTiedosto.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        /**
        * Luodaan yläpalkkiin valikko, josta jo luotuja lemmikkejä valitaan
        */
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

        /**
         * Luodaan uusi paneeli lemmikkien luomiselle
         */
        HBox nimirivi = new HBox();
        nimirivi.getChildren().add(new Label("Lemmikin nimi: "));
        nimirivi.getChildren().add(tfNimi);
        tfNimi.setMaxWidth(100);
        nimirivi.setAlignment(Pos.CENTER);
        HBox lajirivi = new HBox();
        lajirivi.getChildren().add(new Label("Lemmikin laji: "));
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

        /**
         * Luodaan uusi paneeli toimintonapeille
         */
        HBox alaboksi = new HBox();
        alaboksi.getChildren().add(leikiNappi);
        alaboksi.getChildren().add(ruokiNappi);
        alaboksi.getChildren().add(uusiLemmikki);
        alaboksi.setAlignment(Pos.CENTER);
        alaboksi.setSpacing(50);
        alaboksi.setPadding(new Insets(10, 0, 10, 0));
        alaboksi.setStyle("-fx-border-color: gray");

        /**
         * Lisätään yllä mainitut paneelit pohjapaneeliin
         */
        BorderPane pohja = new BorderPane();
        pohja.setTop(ylaboksi);
        pohja.setBottom(alaboksi);

        /**
         * Luodaan ikkuna
         */
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

        /**
         * Luodaan ponnahdusikkuna ilmoittamaan käyttäjälle toimintojen rajoitteista
         */
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

        /**
         * Toiminto <code>luo</code> -napille.
         * Nappia painamalla otetaan luontipaneeliin syötetyt tiedot
         * ja asetetaan ne uuteen <code>Lemmikki</code> -olioon
         */
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
                lemmikkilista.add(new Lemmikki(laji[0], nimi));
                nimilista.add(nimi);
                lemmikkiValinta.setItems(null);
                lemmikkiValinta.setItems(FXCollections.observableArrayList(nimilista));
            }
        });

        /**
         * Luodaan toiminto yläpalkin valintalaatikolle.
         * Lemmikin valittaessa tiedot lemmikistä tallennetaan <code>ArrayList</code> iin
         * ja lemmikin tiedot tulevat näytölle näkyviin.
         * <p>
         * Minulla oli suunnitelmissa piirtää lemmikeistä kuvat, mutta deadlinet ja graafiset taidot tulivat vastaan.
         */
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
                // System.out.println("aikaa viime syötöstä " + (System.currentTimeMillis()-valittu.getViimeSyotto()));
                if(valittu.pitkaAikaSyotosta()){
                    lemmikkiIkkuna.getChildren().add(new Label(valittu.getNimi()+" on nälkäinen."));
                }
                if (valittu.pitkaAikaLeikista()){
                    lemmikkiIkkuna.getChildren().add(new Label(valittu.getNimi()+" on tylsistynyt."));
                }
                lemmikkiIkkuna.setAlignment(Pos.CENTER);
                pohja.setCenter(lemmikkiIkkuna);
            }

        });

        /**
         * Luodaan toiminto alapalkin leiki-napille.
         * Nappi asettaa lemmikin viimeisimmäksi leikkimishetkeksi napinpainallushetken
         * ja päivittää paneelin vastaamaan tilannetta.
         */
        leikiNappi.setOnAction(e -> {
            if(!lemmikkiValinta.getValue().equals("Ei lemmikkejä")) {
                String nimi = lemmikkiValinta.getValue();
                int listanJasen = 0;
                //System.out.println(nimi);
                Lemmikki valittu = null;
                for (int i = 0; i < lemmikkilista.size(); i++) {
                    if (lemmikkilista.get(i).getNimi().equals(nimi)) {
                        valittu = lemmikkilista.get(i);
                        listanJasen = i;
                    }
                }
                valittu.setViimeLeikki();
                //TODO
                // Väliaikainen muotoilu, loppuversioon kuvat
                valittu = lemmikkilista.get(listanJasen);
                VBox lemmikkiIkkuna = new VBox();
                lemmikkiIkkuna.getChildren().add(new Label(valittu.getNimi()));
                lemmikkiIkkuna.getChildren().add(new Label(valittu.getLaji()));
                // System.out.println("aikaa viime leikistä " + (System.currentTimeMillis()-valittu.getViimeLeikki()));
                if(valittu.pitkaAikaSyotosta()){
                    lemmikkiIkkuna.getChildren().add(new Label(valittu.getNimi()+" on nälkäinen."));
                }
                if (valittu.pitkaAikaLeikista()){
                    lemmikkiIkkuna.getChildren().add(new Label(valittu.getNimi()+" on tylsistynyt."));
                }
                lemmikkiIkkuna.setAlignment(Pos.CENTER);
                pohja.setCenter(lemmikkiIkkuna);
            }
        });

        /**
         * Luodaan toiminto alapalkin ruoki-napille.
         * Nappi asettaa lemmikin viimeisimmäksi ruokkimishetkeksi napinpainallushetken
         * ja päivittää paneelin vastaamaan tilannetta.
         */
        ruokiNappi.setOnAction(e -> {
            if(!lemmikkiValinta.getValue().equals("Ei lemmikkejä")) {
                String nimi = lemmikkiValinta.getValue();
                int listanJasen = 0;
                //System.out.println(nimi);
                Lemmikki valittu = null;
                for (int i = 0; i < lemmikkilista.size(); i++) {
                    if (lemmikkilista.get(i).getNimi().equals(nimi)) {
                        valittu = lemmikkilista.get(i);
                        listanJasen = i;
                    }
                }
                valittu.setViimeSyotto();
                //TODO
                // Väliaikainen muotoilu, loppuversioon kuvat
                valittu = lemmikkilista.get(listanJasen);
                VBox lemmikkiIkkuna = new VBox();
                lemmikkiIkkuna.getChildren().add(new Label(valittu.getNimi()));
                lemmikkiIkkuna.getChildren().add(new Label(valittu.getLaji()));
                // System.out.println("aikaa viime syötöstä " + (System.currentTimeMillis()-valittu.getViimeSyotto()));
                if(valittu.pitkaAikaSyotosta()){
                    lemmikkiIkkuna.getChildren().add(new Label(valittu.getNimi()+" on nälkäinen."));
                }
                if (valittu.pitkaAikaLeikista()){
                    lemmikkiIkkuna.getChildren().add(new Label(valittu.getNimi()+" on tylsistynyt."));
                }
                lemmikkiIkkuna.setAlignment(Pos.CENTER);
                pohja.setCenter(lemmikkiIkkuna);
            }
        });

        /**
         * Ikkunan sulkiessa tallennetaan vielä tiedot tiedostoon
         */
        ikkuna.setOnCloseRequest(e ->{
            ObjectOutputStream kTiedosto = null;
            try{
                kTiedosto = new ObjectOutputStream(new FileOutputStream(tiedostoNimi));
                kTiedosto.writeObject(lemmikkilista);
                kTiedosto.close();
            }catch(IOException exception){
                exception.printStackTrace();
            }
        });
    }
}
