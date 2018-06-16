package nl.hu.ipass.persistence;

import nl.hu.ipass.domain.Huis;
import nl.hu.ipass.domain.Slaapplek;
import nl.hu.ipass.domain.Student;

import java.util.List;

/*Interface voor de SlaapplekDao. Deze kan nog door verschillende databases worden geimplementeerd.*/

public interface SlaapplekDao {

    public Slaapplek save(Slaapplek slaapplek);

    public Slaapplek findByStudentAndDatum(Student student, String datum);

    public List<Slaapplek> findByHuisAndDatum(Huis huis, String datum);

    public boolean update(Slaapplek slaapplek);

    public boolean delete(Slaapplek slaapplek);

}
