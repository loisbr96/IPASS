package nl.hu.ipass.services;

import nl.hu.ipass.domain.Huis;
import nl.hu.ipass.domain.Slaapplek;
import nl.hu.ipass.domain.Student;
import nl.hu.ipass.persistence.SlaapplekDao;
import nl.hu.ipass.persistence.SlaapplekDaoImpl;

import java.util.List;

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

//    public Slaapplek findByDatum(String datum){
//        return slaapplekDao.findByDatum(datum);
//    }

//    public List<Slaapplek> findByHuis(Huis huis) {
//        return slaapplekDao.findByHuis(huis);
//    }

//    public Slaapplek findById(int id) {
//        return slaapplekDao.findById(id);
//    }
//
//    public List<Slaapplek> findByStudent(Student student) {
//        return slaapplekDao.findByStudent(student);
//    }

    public List<Slaapplek> findByHuisAndDatum(Huis huis, String datum) {
        return slaapplekDao.findByHuisAndDatum(huis,datum);
    }

    public Slaapplek findByStudentAndDatum(Student student, String datum) {
        return slaapplekDao.findByStudentAndDatum(student,datum);
    }

}
