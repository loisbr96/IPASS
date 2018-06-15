package nl.hu.ipass.domain;

public class Slaapplek {
    private String datum;
    private Huis huis;
    private Student student;

    public Slaapplek(String datum, Huis huis, Student student ){
        this.datum = datum;
        this.huis = huis;
        this.student = student;
    }

    public String getDatum(){
        return datum;
    }

    public void setDatum(String datum){
        this.datum = datum;
    }

    public void setHuis(Huis huis) {
        this.huis = huis;
    }

    public Huis getHuis(){
        return huis;
    }

    public Student getStudent(){
        return student;
    }


}
