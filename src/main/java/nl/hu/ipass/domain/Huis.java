package nl.hu.ipass.domain;

import java.util.ArrayList;

public class Huis {
    private int id;
    private String naam;
    private String straatnaam;
    private int huisnummer;
    private String postcode;
    private String plaatsnaam;
    /*Relatie vastleggen tussen de classes Huis en Slaapplek.*/
    private ArrayList<Slaapplek> alleStudenten = new ArrayList<>();

    /*Constructor Huis zonder id.*/
    public Huis(String naam, String straatnaam, int huisnummer, String postcode, String plaatsnaam){
        this.naam = naam;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.plaatsnaam = plaatsnaam;
    }

    /*Constructor Huis met constructorchaining en het id toegevoegd.*/
    public Huis(int id, String naam, String straatnaam, int huisnummer, String postcode, String plaatsnaam){
        this(naam, straatnaam, huisnummer, postcode, plaatsnaam);
        this.id = id;
    }

    /*Alle getters: */
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

    /*Setter voor naam, andere gegevens kunnen niet worden aangepast*/
    public void setNaam(String naam){
        this.naam = naam;
    }

    /*Getter en Setter voor de ArrayList allestudenten*/
    public ArrayList<Slaapplek> getAlleStudenten(){
        return alleStudenten;
    }

    public void setAlleStudenten(ArrayList<Slaapplek> alleStudenten){
        this.alleStudenten = alleStudenten;
    }

}
