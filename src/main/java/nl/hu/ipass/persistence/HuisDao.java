package nl.hu.ipass.persistence;

import nl.hu.ipass.domain.Huis;

import java.util.List;

/*Interface voor de HuisDao. Deze kan nog door verschillende databases worden geimplementeerd.*/

public interface HuisDao {
    public Huis save(Huis huis);

    public Huis findById(int id);

    public boolean delete(Huis huis);

    public List<Huis> findAll();
}
