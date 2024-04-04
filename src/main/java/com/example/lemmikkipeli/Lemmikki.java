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
        if(viimeSyotto < (getViimeSyotto()-10800000/* 3h */)){
            return true;
        } else {
           return false;
        }
    }

    public boolean pitkaAikaLeikista(){
        if (viimeLeikki < (getViimeLeikki()-5400000/* 1.5 h */)){
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
