package be.intecbrussel.schoolsout.repositories;

import be.intecbrussel.schoolsout.data.Course;
import be.intecbrussel.schoolsout.data.User;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CourseRepository {

    public Course getOneById(long id){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        return em.find(Course.class,id) ;

    }

    public List<Course> getAll(){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        Query query = em.createQuery("Select v from Course v");
        return  query.getResultList();
    }


    public void createOne(Course course){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();

    }

    public void updateOne(Course course){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();

    }

    public void deleteOne(long id){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Course.class,id));
        em.getTransaction().commit();

    }
}
