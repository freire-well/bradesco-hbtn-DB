package models;

import entities.Curso;

import javax.persistence.*;
import java.util.List;

public class CursoModel {

    public void create(Curso c) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();


        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o Curso !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Curso findById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        return em.find(Curso.class, id);
    }

    public List<Curso> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        return em.createQuery("Select u FROM Curso u", Curso.class).getResultList();
    }

    public void update(Curso c, int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        Curso curso = findById(id);
        curso.setNome(c.getNome());
        curso.setSigla(c.getSigla());

        try {
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
        } finally {
            em.close();
        }
    }

    public void delete(Curso c) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
        } finally {
            em.close();
        }
    }
}
