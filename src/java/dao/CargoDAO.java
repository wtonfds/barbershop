/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.Cargo;

/**
 *
 * @author wtonf
 */
public class CargoDAO implements CrudDao<Cargo>{
    @PersistenceContext

    @Override
    public void salvar(Cargo cargo) {
        
        if(cargo.getId() == null){
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cargo);
            em.getTransaction().commit();
        }else{
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.merge(cargo);
            em.getTransaction().commit();
        }
        
    }

    @Override
    public void deletar(Cargo cargo) {
        if(cargo != null){
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery("DELETE cargo FROM Cargo WHERE id = " + cargo.getId());
            q.executeUpdate();
            em.getTransaction().commit();
        }else{
            JOptionPane.showConfirmDialog(null, "Erro ao deletar Cargo");
        }
    }

    @Override
    public List<Cargo> buscar() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT c FROM Cargo c ORDER BY C.nome DESC");
        return (List<Cargo>) q.getResultList();
    }
    
    private EntityManager getEntityManager() {
        EntityManagerFactory  em = Persistence.createEntityManagerFactory("barberShop");
        return em.createEntityManager();
    }

}
