package be.intecbrussel.schoolsout.repositories;

import be.intecbrussel.schoolsout.data.Course;
import be.intecbrussel.schoolsout.data.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserRepository {

 public User getOneById(String login){
     EntityManager em = EMFactory.getEmf().createEntityManager();
     return em.find(User.class,login) ;
 }
    public List <User>getAll(){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        Query query = em.createQuery("Select v from User v", User.class);
        return  query.getResultList();
    }

    public List<User> getALLThatAreActive(){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        Query query = em.createQuery("Select user from User user WHERE user.active=true");
        return query.getResultList();
    }


    public void createOne(User user){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

    }

    public void updateOne(User user){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void deleteOne(String login){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        User user = em.find(User.class,login);
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }
public List<User> getUserByCourse(Course course){
     EntityManager em = EMFactory.getEmf().createEntityManager();
     Query query = em.createQuery("Select g from User g WHERE g.person = :person").setParameter("person",course.getGradesOfCourse());
return  query.getResultList();
 }

}
