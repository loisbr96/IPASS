package nl.hu.ipass.persistence;

import nl.hu.ipass.domain.Huis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HuisDaoImpl extends mysqlBaseDao implements HuisDao {
    @Override
    public Huis save(Huis huis) {
        try(Connection con = super.getConnection()){
            PreparedStatement prep = con.prepareStatement("INSERT INTO huis(naam, straatnaam, huisnummer, postcode, plaatsnaam) VALUES(?,?,?,?,?) ");

            prep.setString(1,huis.getNaam() );
            prep.setString(2, huis.getStraatnaam() );
            prep.setInt(3,huis.getHuisnummer() );
            prep.setString(4,huis.getPostcode() );
            prep.setString(5,huis.getPlaatsnaam() );

            prep.execute();
            return huis;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean delete(Huis huis) {
        try(Connection con = super.getConnection() ){
            PreparedStatement prep = con.prepareStatement("DELETE FROM huis WHERE id = ?");
            prep.setInt(1,huis.getId() );
            prep.execute();
            return true;
        } catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Huis findById(int id) {
        try(Connection con = super.getConnection()) {
            PreparedStatement prep = con.prepareStatement("SELECT * FROM huis WHERE id = ? ");
            prep.setInt(1,id );

            ResultSet result = prep.executeQuery();
            result.next();
            return new Huis(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5), result.getString(6) );
        } catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Huis> findAll(){
        List<Huis> results = new ArrayList<Huis>();
        try(Connection con = super.getConnection()){
            PreparedStatement prep = con.prepareStatement("SELECT * FROM huis");
            ResultSet resultSet = prep.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String naam = resultSet.getString("naam");
                String straatnaam = resultSet.getString("straatnaam");
                int huisnummer = resultSet.getInt("huisnummer");
                String postcode = resultSet.getString("postcode");
                String plaatsnaam = resultSet.getString("plaatsnaam");

                results.add(new Huis(id, naam, straatnaam,huisnummer ,postcode ,plaatsnaam ));
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return results;
    }




}
