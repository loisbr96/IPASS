package nl.hu.ipass.persistence;

import nl.hu.ipass.domein.Slaapplek;
import nl.hu.ipass.domein.Student;

import java.util.List;

public interface StudentDao {
    public Student save(Student student);

    public Student findById(int id);

    public Student findEmailForWachtwoord(String email, String wachtwoord);

    public boolean update(Student student);

    public boolean delete(Student student);
}
