package nl.hu.ipass.persistence;

import nl.hu.ipass.domain.Huis;
import nl.hu.ipass.domain.Slaapplek;
import nl.hu.ipass.domain.Student;
import nl.hu.ipass.services.HuisService;
import nl.hu.ipass.services.ServiceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SlaapplekDaoImpl extends mysqlBaseDao implements SlaapplekDao {

    @Override
    public Slaapplek save(Slaapplek slaapplek) {
        try(Connection con = super.getConnection()){
            PreparedStatement prep = con.prepareStatement("INSERT INTO slaapplek(datum, huisId, studentId) VALUES(?,?,?)");
            prep.setString(1,slaapplek.getDatum() );
            prep.setInt(2,slaapplek.getHuis().getId() );
            prep.setInt(3,slaapplek.getStudent().getId() );
            prep.execute();
            return slaapplek;
        } catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean update(Slaapplek slaapplek) {
        try(Connection con = super.getConnection()) {
            PreparedStatement prep = con.prepareStatement("UPDATE slaapplek SET datum = ?, huisId = ?, studentId = ? WHERE id = ? ");
            prep.setString(1,slaapplek.getDatum() );
            prep.setInt(2,slaapplek.getHuis().getId() );
            prep.setInt(3,slaapplek.getStudent().getId() );
            prep.setInt(4,slaapplek.getId() );

            prep.execute();
            return true;
        } catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Slaapplek slaapplek) {
        try(Connection con = super.getConnection()){
            PreparedStatement prep = con.prepareStatement("DELETE FROM slaapplek WHERE id = ?");

            prep.setInt(1,slaapplek.getId() );

            prep.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }

    private List<Slaapplek> selectSlaapplek(String query){
        List<Slaapplek> results = new ArrayList<Slaapplek>();
        try(Connection con = super.getConnection()){
            PreparedStatement prep = con.prepareStatement(query);
            ResultSet rs = prep.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String datum = rs.getString("datum");
                Huis huis = ServiceProvider.getHuisService().findById(rs.getInt("huisId"));
                Student student = ServiceProvider.getStudentService().findById(rs.getInt("studentId"));

                results.add(new Slaapplek(id, datum, huis, student));
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return results;
    }

    @Override
    public List<Slaapplek> findByDatum(String datum) {
        return selectSlaapplek("SELECT * FROM slaapplek WHERE datum = '" + datum + "'");
    }

    @Override
    public List<Slaapplek> findByHuis(Huis huis) {
        return selectSlaapplek("SELECT * FROM slaapplek WHERE huisId = '" + huis.getId() + "'");
    }

    @Override
    public Slaapplek findById(int id) {
        return selectSlaapplek("SELECT * FROM slaapplek WHERE id = '" + id + "'").get(0);
    }

    @Override
    public List<Slaapplek> findByStudent(Student student) {
        return selectSlaapplek("SELECT * FROM slaapplek WHERE studentId = '" + student.getId() + "'");
    }

    @Override
    public List<Slaapplek> findByHuisAndDatum(Huis huis, String datum) {
        return selectSlaapplek("SELECT * FROM slaapplek WHERE huisId = '" + huis.getId() + "' AND datum = '" + datum + "'");
    }

    @Override
    public Slaapplek findByStudentAndDatum(Student student, String datum) {
        return selectSlaapplek("SELECT * FROM slaapplek WHERE studentId = '" + student.getId() + "' AND datum = '" + datum + "'").get(0);
    }

}

