package nl.hu.ipass.persistence;

import nl.hu.ipass.domain.Huis;

public interface HuisDao {
    public Huis save(Huis huis);

    public Huis findById(int id);

    public boolean delete(Huis huis);
}
