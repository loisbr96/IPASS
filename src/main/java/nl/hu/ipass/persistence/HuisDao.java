package nl.hu.ipass.persistence;

import nl.hu.ipass.domein.Huis;

public interface HuisDao {
    public Huis save(Huis student);

    public Huis findById(int id);

    public boolean update(Huis student);

    public boolean delete(Huis student);
}
