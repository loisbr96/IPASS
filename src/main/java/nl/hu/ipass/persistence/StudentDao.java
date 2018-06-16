package nl.hu.ipass.persistence;

import nl.hu.ipass.domain.Student;

/*Interface voor de StudentDao. Deze kan nog door verschillende databases worden geimplementeerd.*/

public interface StudentDao {
    public Student save(Student student);

    public Student findById(int id);

    public Student findByEmailAndWachtwoord(String email, String wachtwoord);

    public boolean update(Student student);

    public boolean delete(Student student);
}
