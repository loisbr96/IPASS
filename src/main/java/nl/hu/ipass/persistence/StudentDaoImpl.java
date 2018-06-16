package nl.hu.ipass.persistence;

import nl.hu.ipass.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoImpl extends mysqlBaseDao implements StudentDao {

    /*Methode om een nieuwe student toe te voegen: */
    @Override
    public Student save(Student student) {
        try(Connection con = super.getConnection()){
            PreparedStatement prep = con.prepareStatement("INSERT INTO student(voornaam, tussenvoegsel, achternaam, email, wachtwoord) VALUES (?,?,?,?,?) ");
            prep.setString(1,student.getVoornaam() );
            prep.setString(2,student.getTussenvoegsel() );
            prep.setString(3,student.getAchternaam() );
            prep.setString(4,student.getEmail() );
            prep.setString(5, student.getWachtwoord());

            prep.execute();
            return student;
        } catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    /*Methode om van een bestaande student de gegevens te wijzigen */
    @Override
    public boolean update(Student student) {
        try(Connection con = super.getConnection()){
            PreparedStatement prep = con.prepareStatement("UPDATE student SET voornaam = ?, tussenvoegsel = ?, achternaam = ? , email = ?, wachtwoord = ? WHERE id = ?");

            prep.setString(1,student.getVoornaam() );
            prep.setString(2, student.getTussenvoegsel() );
            prep.setString(3,student.getAchternaam() );
            prep.setString(4, student.getEmail() );
            prep.setString(5,student.getWachtwoord() );
            prep.setInt(6,student.getId() );

            prep.execute();
            return true;
        } catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }

    /*Methode om een student uit de database te verwijderen: */
    @Override
    public boolean delete(Student student) {
        try(Connection con = super.getConnection()){
            PreparedStatement prep = con.prepareStatement("DELETE FROM student WHERE id = ? ");

            prep.setInt(1,student.getId() );

            prep.execute();
            return true;
        } catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }

    /*Methode om via een id van de student alle gegevens te verkrijgen van die student: */
    @Override
    public Student findById(int id) {
        try(Connection con = super.getConnection()) {
            PreparedStatement prep = con.prepareStatement("SELECT * FROM student WHERE id = ? ");
            prep.setInt(1,id );

            ResultSet result = prep.executeQuery();
            result.next();
            return new Student(result.getInt(1), result.getString(2), result.getString(3), ((ResultSet) result).getString(4), result.getString(5), result.getString(6) );
        } catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    /*Methode voor het inloggen. Er worden gecontroleerd of de email en wachtwoord samen overeen komen. */
    @Override
    public Student findByEmailAndWachtwoord(String email, String wachtwoord) {
        try(Connection con = super.getConnection()){
            PreparedStatement prep = con.prepareStatement("SELECT * FROM student WHERE email = ? AND wachtwoord = ?");
            prep.setString(1,email );
            prep.setString(2,wachtwoord );

            ResultSet result = prep.executeQuery();
            result.next();
            return new Student(result.getInt(1), result.getString(2), result.getString(3), ((ResultSet) result).getString(4), result.getString(5), result.getString(6) );
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }
}
