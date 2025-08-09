package models;

import entities.Produto;
import entities.Produto;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProdutoModel{

    public void create(Produto p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();


        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Produto findById(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        return em.find(Produto.class, id);
    }

    public List<Produto> findAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
       return em.createQuery("Select u FROM produto u", Produto.class).getResultList();
    }

    public void update(Produto p, int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        Produto prod = findById(id);
        prod.setPreco(p.getPreco());
        prod.setNome(p.getNome());
        prod.setQuantidade(p.getQuantidade());
        prod.setStatus(p.getStatus());

        try{
            em.getTransaction().begin();
            em.persist(prod);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
        }finally {
            em.close();
        }


    }
    public void delete(Produto p){
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
