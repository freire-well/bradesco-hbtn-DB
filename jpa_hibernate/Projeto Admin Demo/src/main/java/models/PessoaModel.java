package models;

import entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PessoaModel {

    public void create(Pessoa p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();


        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o Pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }
    public Pessoa findById(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        return em.find(Pessoa.class, id);
    }
    public List<Pessoa> findAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        return em.createQuery("Select u FROM Pessoa u", Pessoa.class).getResultList();
    }
    public void update(Pessoa p, int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa pess = p;

        try{
            em.getTransaction().begin();
            em.merge(pess);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
        }finally {
            em.close();
        }


    }
    public void delete(Pessoa p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
        }finally {
            em.close();
        }
    }
}
