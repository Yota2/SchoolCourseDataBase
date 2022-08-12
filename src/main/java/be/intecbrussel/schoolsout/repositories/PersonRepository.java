package be.intecbrussel.schoolsout.repositories;
import be.intecbrussel.schoolsout.data.Person;
import be.intecbrussel.schoolsout.data.User;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.List;
public class PersonRepository {
    private  EntityManagerFactory emf;
    public PersonRepository(){
        emf =EMFactory.getEmf();
    }
    public  Person getPersonByName(int id){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        return em.find(Person.class,id);
    }
    public  List<Person>getAllPerson(){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        Query query = em.createQuery("Select v from Person v");
        return query.getResultList();
    }
    public  void updatePerson(Person person){
        EntityManager em= emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
    }
}

