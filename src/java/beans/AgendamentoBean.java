/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AgendamentoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Agendamento;

/**
 *
 * @author wtonf
 */
@SessionScoped
@ManagedBean
public class AgendamentoBean extends CrudBean<Agendamento, AgendamentoDAO>{
    
    private AgendamentoDAO agdmtDao;

    @Override
    public AgendamentoDAO getDao() {
        if(agdmtDao == null)
            agdmtDao = new AgendamentoDAO();
        return agdmtDao;
        
    }

    @Override
    public Agendamento criarNovaEntidade() {
        return new Agendamento();
    }
    
    public List<String> listarAll() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT nome FROM servico");
        return (List<String>) q.getResultList();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("barberShop");
        return em.createEntityManager();
    }

}
