package nl.hu.ipass.services;

import nl.hu.ipass.domain.Student;
import nl.hu.ipass.persistence.StudentDao;
import nl.hu.ipass.persistence.StudentDaoImpl;

public class StudentService {
    private StudentDao studentDao;

    public StudentService(){
        studentDao = new StudentDaoImpl();
    }

    public Student save(Student student){
        return studentDao.save(student);
    }

    public boolean update(Student student){
        return studentDao.update(student);
    }


    public boolean delete(Student student){
        return studentDao.delete(student);
    }

    public Student findById(int id){
        return studentDao.findById(id);
    }

    public Student findByEmailAndWachtwoord(String email, String wachtwoord){
        return studentDao.findByEmailAndWachtwoord(email,wachtwoord);
    }

}
