package models;

import entities.Aluno;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class AlunoModel {

    public void create(Aluno a){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();


        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o Aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        return em.find(Aluno.class, id);
    }

    public List<Aluno> findAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        return em.createQuery("Select u FROM Aluno u", Aluno.class).getResultList();
    }

    public void update(Aluno a, int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        Aluno aluno = findById(id);
        aluno.setEmail(a.getEmail());
        aluno.setNomeCompleto(a.getNomeCompleto());
        aluno.setNascimento(a.getNascimento());
        aluno.setMatricula(a.getMatricula());

        try{
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
        }finally {
            em.close();
        }
    }

    public void delete(Aluno a){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
        }finally {
            em.close();
        }
    }
}
