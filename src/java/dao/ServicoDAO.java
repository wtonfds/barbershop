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
import model.Servico;

/**
 *
 * @author wtonf
 */
public class ServicoDAO implements CrudDao<Servico>{
    @PersistenceContext

    @Override
    public void salvar(Servico servico) {
        if(servico.getId() == null){
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.persist(servico);
            em.getTransaction().commit();
        }else{
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.merge(servico);
            em.getTransaction().commit();
        }
        
    }

    @Override
    public void deletar(Servico servico) {
        if(servico != null){
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createQuery("DELETE servico FROM Servico WHERE id = " + servico.getId());
            q.executeUpdate();
            em.getTransaction().commit();
        }else{
            JOptionPane.showConfirmDialog(null, "Erro ao deletar Serviço");
        }
    }

    @Override
    public List<Servico> buscar() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Servico s ORDER BY s.nome DESC");
        return (List<Servico>) q.getResultList();
    }
    
    private EntityManager getEntityManager() {
        EntityManagerFactory  em = Persistence.createEntityManagerFactory("barberShop");
        return em.createEntityManager();
    }
}
