package nl.hu.ipass.domain;

public class Slaapplek {
    private String datum;
    private Huis huis; /*Relatie tussen Slaapplek en Huis*/
    private Student student; /*Relatie tussen Slaapplek en Student*/

    /*Constructor:*/
    public Slaapplek(String datum, Huis huis, Student student ){
        this.datum = datum;
        this.huis = huis;
        this.student = student;
    }

    /*Getters:*/
    public String getDatum(){
        return datum;
    }

    public Huis getHuis(){
        return huis;
    }

    public Student getStudent(){
        return student;
    }

    /*Setters:*/
    public void setDatum(String datum){
        this.datum = datum;
    }

    public void setHuis(Huis huis) {
        this.huis = huis;
    }


}
