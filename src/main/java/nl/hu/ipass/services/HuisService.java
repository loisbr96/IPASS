package nl.hu.ipass.services;

import nl.hu.ipass.domain.Huis;
import nl.hu.ipass.persistence.HuisDao;
import nl.hu.ipass.persistence.HuisDaoImpl;

import java.util.List;

public class HuisService {
    private HuisDao huisDao;

    public HuisService(){
        huisDao = new HuisDaoImpl();
    }

    public Huis save(Huis huis){
        return huisDao.save(huis);
    }

    public boolean delete(Huis huis){
        return huisDao.delete(huis);
    }

    public Huis findById(int id){
        return huisDao.findById(id);
    }

    public List<Huis> findAll(){
        return huisDao.findAll();
    }

}
