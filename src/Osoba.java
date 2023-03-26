/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.edu.wsiz.lab4;

import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;

/**
 *
 * @author leszek
 */
public class Osoba {
    private String imie = null;
    private String nazwisko = null;
    private int rok = 0;
    private Plec plec = null;

    public enum Plec{
        M,K
    }
    
    public Osoba() {
    }

    public Osoba(String imie, String nazwisko, int rok, Plec plec) {
        this.imie= imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        this.rok = rok;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }    
    
    public String getNazwisko(){
        return nazwisko;
    }
    
    public void setNazwisko(String nazwisko){
        this.nazwisko=nazwisko;
    }

    /**
     * @return the rok
     */
    public int getRok() {
        return rok;
    }

    /**
     * @param rok the rok to set
     */
    public void setRok(int rok) {
        this.rok = rok;
    }
    
    public int wiek(){
        return LocalDateTime.now().getYear()-rok;
    }

    /**
     * @return the plec
     */
    public Plec getPlec() {
        return plec;
    }

    /**
     * @param plec the plec to set
     */
    public void setPlec(Plec plec) {
        this.plec = plec;
    }
    
    public void wypisz(){
        System.out.println(imie+" "+nazwisko+","+plec+", rok ur.:"+rok+", wiek:"+wiek());
    }

}