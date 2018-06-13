package nl.hu.ipass.domein;

public class Student {
    private int persoonId;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String email;
    protected String wachtwoord;

    public Student(int persoonId, String voornaam, String tussenvoegsel, String achternaam, String email, String wachtwoord){
        this.persoonId = persoonId;
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
    }

    /*constructor 2 toevoegen*/

    public int getPersoonId(){
        return persoonId;
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
        return voornaam + tussenvoegsel + achternaam;
    }

    public String getEmail(){
        return email;
    }

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
        if(this.wachtwoord.equals(oudWachtwoord)){
            this.wachtwoord = nieuwWachtwoord;
        }
    }

    public boolean wachtwoordCorrect(String wachtwoord){
        if(this.wachtwoord.equals(wachtwoord)){
            return true;
        }else{
            return false;
        }
    }
}
