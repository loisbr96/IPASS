package nl.hu.ipass.persistence;

import nl.hu.ipass.domain.Huis;

import java.util.List;

public interface HuisDao {
    public Huis save(Huis huis);

    public Huis findById(int id);

    public boolean delete(Huis huis);

    public List<Huis> findAll();
}
