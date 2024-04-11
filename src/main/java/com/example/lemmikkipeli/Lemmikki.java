package com.example.lemmikkipeli;

import java.io.Serializable;

/**
 * Olioluokka lemmikkipeliin, implementoi rajapinnan <code>Serializable</code> luokan tallentamista varten.
 *
 * @author Olli Tirkkonen
 */
public class Lemmikki implements Serializable {

    private String laji;
    private String nimi;
    private long viimeSyotto;
    private long viimeLeikki;

    /**
     * Parametriton alustaja luokalle. Tätä alustajaa ei käytetä lopullisessa ohjelmassa,
     * mutta sen on silti hyvä olla olemassa. Alustaja myös nollaa viimeLeikki- ja viimeSyottolaskurit
     */
    public Lemmikki(){
        this.laji = "";
        this.nimi = "";
        setViimeLeikki();
        setViimeSyotto();
    }

    /**
     * Parametrillinen alustaja luokalle. Kuten parametriton alustaja,
     * alustaja nollaa <code>viimeLeikki</code> ja <code>viimeSyotto</code> laskurit.
     * @param laji  Syötetään lemmikin laji.
     * @param nimi  Syötetään lemmikin laji.
     */
    public Lemmikki(String laji, String nimi){
        this.laji = laji;
        this.nimi = nimi;
        setViimeLeikki();
        setViimeSyotto();
    }

    /**
     * Set-metodi muuttujalle <code>laji</code>.
     * @param laji Lemmikin laji
     */
    public void setLaji(String laji){
        this.laji = laji;
    }

    /**
     * Set-metodi muuttujalle <code>nimi</code>
     * @param nimi  Lemmikin nimi
     */
    public void setNimi(String nimi){
        this.nimi = nimi;
    }

    /**
     * Set-metodi muuttujalle <code>viimeSyotto</code>. Metodi ottaa tietokoneen nykyisen Unix-ajan.
     */
    public void setViimeSyotto(){
        this.viimeSyotto = System.currentTimeMillis();
    }

    /**
     * Set-metodi muuttujalle <code>viimeLeikki</code>. Metodi ottaa tietokoneen nykyisen Unix-ajan.
     */
    public void setViimeLeikki(){
        this.viimeLeikki = System.currentTimeMillis();
    }

    /**
     * Palauttaa muuttujan <code>laji</code>
     */
    public String getLaji(){
        return laji;
    }

    /**
     * Palauttaa muuttujan <code>nimi</code>
     */
    public String getNimi(){
        return nimi;
    }

    /**
     * Palauttaa muuttujan <code>viimeSyotto</code>
     */
    public long getViimeSyotto(){
        return viimeSyotto;
    }

    /**
     * Palauttaa muuttujan <code>viimeLeikki</code>
     */
    public long getViimeLeikki(){
        return viimeLeikki;
    }

    /**
     * Testataan, onko syötöstö kulunut pitkä aika. Oletusarvona 6 sekuntia.
     * Aluksi suunniteltu olevan 3 tuntia, eli 10 800 000 ms.
     * Palauttaa <code>true</code> ajan ollessa pidempi kuin 6 sekuntia ja palauttaa muulloin <code>false</code>
     */
    public boolean pitkaAikaSyotosta(){
        int aikaS = 6000;
        if(System.currentTimeMillis() > getViimeSyotto() + aikaS){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Testataan, onko leikistä kulunut pitkä aika. Oletusarvona 3 sekuntia.
     * Aluksi suunniteltu olevan 1.5 tuntia, eli 5 400 000 ms.
     * Palauttaa <code>true</code> ajan ollessa pidempi kuin 3 sekuntia ja palauttaa muulloin <code>false</code>
     */
    public boolean pitkaAikaLeikista(){
        int aikaL = 3000;
        if (System.currentTimeMillis() > getViimeLeikki()+aikaL){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Debuggausta varten luotu <code>toString</code> -metodi.
     * Palauttaa lemmikin lajin ja nimen <code>String</code>-muodossa
     */
    @Override
    public String toString(){
        return "laji: " +laji+"\nnimi: "+nimi;
    }
}
