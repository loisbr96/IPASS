package nl.hu.ipass.domain;

public class Slaapplek {
    private int id;
    private String datum;
    private Huis huis;
    private Student student;

    public Slaapplek(String datum, Huis huis, Student student ){
        this.datum = datum;
        this.huis = huis;
        this.student = student;
    }

    public Slaapplek(int id, String datum, Huis huis, Student student ){
        this(datum, huis, student);
        this.id = id;
    }

    public String getDatum(){
        return datum;
    }

    public int getId(){
        return id;
    }

    public void setDatum(String datum){
        this.datum = datum;
    }

    public Huis getHuis(){
        return huis;
    }

    public Student getStudent(){
        return student;
    }


}
