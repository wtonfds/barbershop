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
import model.Agendamento;

/**
 *
 * @author wtonf
 */
public class AgendamentoDAO implements CrudDao<Agendamento>{
    @PersistenceContext

    @Override
    public void salvar(Agendamento agendamento) {
        if(agendamento.getId() == null){
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.persist(agendamento);
            em.getTransaction().commit();  
        }else{
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(agendamento);
        em.getTransaction().commit();
        
        }
    }

    @Override
    public void deletar(Agendamento agendamento) {
        if(agendamento != null){
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery("DELETE agendamento FROM Agendamento WHERE id = "+ agendamento.getId());
            q.executeUpdate();
            em.getTransaction().commit();        
        }else{
            JOptionPane.showConfirmDialog(null, "Erro ao deletar Agendamento");
        }
    }

    @Override
    public List<Agendamento> buscar() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT a FROM Agendamento a ORDER BY a.nome DESC");
        return (List<Agendamento>) q.getResultList();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory  em = Persistence.createEntityManagerFactory("barberShop");
        return em.createEntityManager();
    }

}
