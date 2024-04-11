package com.example.lemmikkipeli;

import java.io.Serializable;

public class Lemmikki implements Serializable {

    private String laji;
    private String nimi;
    private long viimeSyotto;
    private long viimeLeikki;

    public Lemmikki(){
        this.laji = "";
        this.nimi = "";
        setViimeLeikki();
        setViimeSyotto();
    }

    public Lemmikki(String laji, String nimi){
        this.laji = laji;
        this.nimi = nimi;
        setViimeLeikki();
        setViimeSyotto();
    }

    public void setLaji(String laji){
        this.laji = laji;
    }

    public void setNimi(String nimi){
        this.nimi = nimi;
    }

    public void setViimeSyotto(){
        this.viimeSyotto = System.currentTimeMillis();
    }

    public void setViimeLeikki(){
        this.viimeLeikki = System.currentTimeMillis();
    }

    public String getLaji(){
        return laji;
    }

    public String getNimi(){
        return nimi;
    }

    public long getViimeSyotto(){
        return viimeSyotto;
    }

    public long getViimeLeikki(){
        return viimeLeikki;
    }

    public boolean pitkaAikaSyotosta(){
        /*
        // 3h
        if(viimeSyotto < (getViimeSyotto()-10800000)){
            return true;
        } else {
           return false;
        }
        */
        // 6 s debuggaukseen
        if(System.currentTimeMillis() > getViimeSyotto() + 6000){
            return true;
        } else {
            return false;
        }
    }

    public boolean pitkaAikaLeikista(){
        /*
        // 1.5 h
        if (viimeLeikki < (getViimeLeikki()-5400000)){
            return true;
        } else {
            return false;
        }
        */
        // 3 s debuggaukseen
        if (System.currentTimeMillis() > getViimeLeikki()+3000){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return "laji: " +laji+"\nnimi: "+nimi;
    }
}
