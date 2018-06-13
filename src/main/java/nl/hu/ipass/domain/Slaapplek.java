package nl.hu.ipass.domain;

public class Slaapplek {
    private String status;
    private String datum;
    private Huis huis;
    private Student student;

    public Slaapplek(String status, String datum){
        this.status = status;
        this.datum = datum;
    }

    public String getStatus(){
        return status;
    }

    public String getDatum(){
        return datum;
    }

    public void setDatum(String datum){
        this.datum = datum;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
