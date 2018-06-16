package nl.hu.ipass.services;

import nl.hu.ipass.domain.Huis;
import nl.hu.ipass.domain.Slaapplek;
import nl.hu.ipass.domain.Student;
import nl.hu.ipass.persistence.SlaapplekDao;
import nl.hu.ipass.persistence.SlaapplekDaoImpl;

import java.util.List;

/*Deze service geeft het resultaat van de dao*/
public class SlaapplekService {
    private SlaapplekDao slaapplekDao;

    public SlaapplekService(){
        slaapplekDao = new SlaapplekDaoImpl();
    }

    public Slaapplek save(Slaapplek slaapplek){
        return slaapplekDao.save(slaapplek);
    }

    public boolean update(Slaapplek slaapplek){
        return slaapplekDao.update(slaapplek);
    }

    public boolean delete(Slaapplek slaapplek){
        return slaapplekDao.delete(slaapplek);
    }

    public List<Slaapplek> findByHuisAndDatum(Huis huis, String datum) {
        return slaapplekDao.findByHuisAndDatum(huis,datum);
    }

    public Slaapplek findByStudentAndDatum(Student student, String datum) {
        return slaapplekDao.findByStudentAndDatum(student,datum);
    }

}
