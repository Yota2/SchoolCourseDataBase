package be.intecbrussel.schoolsout.repositories;


import be.intecbrussel.schoolsout.data.Course;
import be.intecbrussel.schoolsout.data.Grade;
import be.intecbrussel.schoolsout.data.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;

public class GradeRepository {

    public Grade getOneById(long id) {
        EntityManager em = EMFactory.getEmf().createEntityManager();
        return em.find(Grade.class, id);
    }

    public List<Grade> getAll() {
        EntityManager em = EMFactory.getEmf().createEntityManager();
       Query query = em.createQuery("Select v from Grade v");
        return query.getResultList();
    }


    public void createOne(Grade grade) {
        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.persist(grade);
        em.getTransaction().commit();

    }

    public void updateOne(Grade grade) {
        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.merge(grade);
        em.getTransaction().commit();
    }

    public void deleteOne(long id) {
        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Grade.class,id));
        em.getTransaction().commit();
    }



        public List<Grade> findAllGradesForUser(User user){
            EntityManager em = EMFactory.getEmf().createEntityManager();
            Query query = em.createQuery("Select g from Grade g WHERE g.person = :person").setParameter("person", user.getPerson());
            return query.getResultList();

    }


}
