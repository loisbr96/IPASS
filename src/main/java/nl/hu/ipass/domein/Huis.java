package nl.hu.ipass.domein;

import java.util.ArrayList;

public class Huis {
    private int id;
    private String naam;
    private String straatnaam;
    private int huisnummer;
    private String postcode;
    private String plaatsnaam;
    private ArrayList<Slaapplek> alleStudenten = new ArrayList<>();

    public Huis(String naam, String straatnaam, int huisnummer, String postcode, String plaatsnaam){
        this.naam = naam;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.plaatsnaam = plaatsnaam;
    }

    public Huis(int id, String naam, String straatnaam, int huisnummer, String postcode, String plaatsnaam){
        this(naam, straatnaam, huisnummer, postcode, plaatsnaam);
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getNaam(){
        return naam;
    }

    public String getStraatnaam(){
        return straatnaam;
    }

    public int getHuisnummer(){
        return huisnummer;
    }

    public String getPostcode(){
        return postcode;
    }

    public String getPlaatsnaam(){
        return plaatsnaam;
    }

    public void setNaam(String naam){
        this.naam = naam;
    }

    public ArrayList<Slaapplek> getAlleStudenten(){
        return alleStudenten;
    }

    public void setAlleStudenten(ArrayList<Slaapplek> alleStudenten){
        this.alleStudenten = alleStudenten;
    }

}
