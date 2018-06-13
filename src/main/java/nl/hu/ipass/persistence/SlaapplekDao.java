package nl.hu.ipass.persistence;

import nl.hu.ipass.domein.Huis;
import nl.hu.ipass.domein.Slaapplek;
import nl.hu.ipass.domein.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface SlaapplekDao {

    public Slaapplek save(Slaapplek slaapplek);

    public List<Slaapplek> findByDatum(String datum);

    public List<Slaapplek> findByHuis(Huis huis);

    public List<Slaapplek> findByStudent(Student student);

    public Slaapplek findByStudentAndDatum(Student student, String datum);

    public List<Slaapplek> findByHuisAndDatum(Huis huis, String datum);

    public boolean update(Slaapplek slaapplek);

    public boolean delete(Slaapplek slaapplek);

}
