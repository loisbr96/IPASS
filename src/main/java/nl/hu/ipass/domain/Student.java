package nl.hu.ipass.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;

public class Student {
    private int id;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String email;
    private String wachtwoord;
    private ArrayList<Slaapplek> alleSlaapplekken = new ArrayList<>(); /*Relatie tussen Student en Slaapplek*/

    /*Constructor zonder id*/
    public Student(String voornaam, String tussenvoegsel, String achternaam, String email, String wachtwoord){
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = DigestUtils.sha256Hex(wachtwoord);
    }

    /*Constructor met id d.m.v. constructorchaining*/
    public Student(int id, String voornaam, String tussenvoegsel, String achternaam, String email, String wachtwoord){
        this(voornaam, tussenvoegsel, achternaam, email,wachtwoord);
        this.id = id;
    }

    /*Getters:*/
    public int getId(){
        return id;
    }

    public String getVoornaam(){
        return voornaam;
    }

    public String getTussenvoegsel(){
        return tussenvoegsel;
    }

    public String getAchternaam(){
        return achternaam;
    }

    public String getVolledigeNaam(){
        String volledigeNaam;
        if(tussenvoegsel == null){
            volledigeNaam = voornaam + " " + achternaam;
        } else{
            volledigeNaam = voornaam + " " + tussenvoegsel + " " + achternaam;
        }
        return volledigeNaam;
    }

    public String getEmail(){
        return email;
    }

    public String getWachtwoord(){
        return wachtwoord;
    }

    /*Setters:*/
    public void setVoornaam(String voornaam){
        this.voornaam = voornaam;
    }

    public void setTussenvoegsel(String tussenvoegsel){
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setAchternaam(String achternaam){
        this.achternaam = achternaam;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setWachtwoord(String oudWachtwoord, String nieuwWachtwoord){
        this.wachtwoord = DigestUtils.sha256Hex(nieuwWachtwoord);
    }

    /*Methode om te controleren of het wachtwoord correct is:*/
    public boolean wachtwoordCorrect(String wachtwoord){
        if(this.wachtwoord.equals(DigestUtils.sha256Hex(wachtwoord))){
            return true;
        }else{
            return false;
        }
    }

    /*Getter en setter voor de Arraylist alleSlaapplekken:*/
    public ArrayList<Slaapplek> getAlleSlaapplekken(){
        return alleSlaapplekken;
    }

    public void setAlleSlaapplekken(ArrayList<Slaapplek> alleSlaapplekken){
        this.alleSlaapplekken = alleSlaapplekken;
    }
}
