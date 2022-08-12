package be.intecbrussel.schoolsout.repositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFactory {

    public static EntityManagerFactory getEmf(){
        return Persistence.createEntityManagerFactory("pearlDataBase");
//movieDataBase
        //pearlDataBase
    }
}
