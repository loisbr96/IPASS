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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SlaapplekDaoImpl extends mysqlBaseDao implements SlaapplekDao {

    @Override
    public Slaapplek save(Slaapplek slaapplek) {
        try(Connection con = super.getConnection()){
            PreparedStatement prep = con.prepareStatement("INSERT INTO slaapplek(datum, huisId, studentId) VALUES(?,?,?)");
            try{
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Date datum =  df.parse(slaapplek.getDatum());
                java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
                prep.setDate(1, sqlDatum );
                prep.setInt(2,slaapplek.getHuis().getId() );
                prep.setInt(3,slaapplek.getStudent().getId() );
                prep.execute();

            } catch (ParseException pe){
                System.out.println(pe);
            }
            return slaapplek;
        } catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean update(Slaapplek slaapplek) {
        try(Connection con = super.getConnection()) {
            PreparedStatement prep = con.prepareStatement("UPDATE slaapplek SET huisId = ? WHERE datum = STR_TO_DATE(?, '%d-%m-%Y') AND studentId = ?");
            prep.setInt(1, slaapplek.getHuis().getId());
            prep.setString(2, slaapplek.getDatum());
            prep.setInt(3, slaapplek.getStudent().getId());

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
            PreparedStatement prep = con.prepareStatement("DELETE FROM slaapplek WHERE studentId = ? AND datum = ?");
            try{
                prep.setInt(1,slaapplek.getStudent().getId() );
                DateFormat df = new SimpleDateFormat("dd-MM-YYYY");
                Date datum = df.parse(slaapplek.getDatum());
                java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
                prep.setDate(2,sqlDatum );
            } catch (ParseException pe){
                System.out.println(pe);
            }

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
                DateFormat df = new SimpleDateFormat("dd-MM-YYYY");
                String datum = df.format(new Date(rs.getDate("datum").getTime()));
                Huis huis = ServiceProvider.getHuisService().findById(rs.getInt("huisId"));
                Student student = ServiceProvider.getStudentService().findById(rs.getInt("studentId"));

                results.add(new Slaapplek(datum, huis, student));
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return results;
    }

//    @Override
//    public Slaapplek findByDatum(String datum) {
//        return selectSlaapplek("SELECT * FROM slaapplek WHERE datum = STR_TO_DATE('" + datum + "', '%d-%m-%Y')").get(0);
//    }

//    @Override
//    public List<Slaapplek> findByHuis(Huis huis) {
//        return selectSlaapplek("SELECT * FROM slaapplek WHERE huisId = '" + huis.getId() + "'");
//    }

//    @Override
//    public Slaapplek findById(int id) {
//        return selectSlaapplek("SELECT * FROM slaapplek WHERE id = " + id).get(0);
//    }

//    @Override
//    public List<Slaapplek> findByStudent(Student student) {
//        return selectSlaapplek("SELECT * FROM slaapplek WHERE studentId = '" + student.getId() + "'");
//    }

    @Override
    public List<Slaapplek> findByHuisAndDatum(Huis huis, String datum) {
        return selectSlaapplek("SELECT * FROM slaapplek WHERE huisId = " + huis.getId() + " AND datum = STR_TO_DATE('" + datum + "', '%d-%m-%Y')");
    }

    @Override
    public Slaapplek findByStudentAndDatum(Student student, String datum) {
        return selectSlaapplek("SELECT * FROM slaapplek WHERE studentId = " + student.getId() + " AND datum = STR_TO_DATE('" + datum + "', '%d-%m-%Y')").get(0);
    }

}

