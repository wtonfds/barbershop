/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CrudDao;
import dao.ServicoDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Servico;

/**
 *
 * @author wtonf
 */
@SessionScoped
@ManagedBean
public class ServicoBean extends CrudBean<Servico, ServicoDAO>{
    
    private ServicoDAO servicoDao;

    @Override
    public ServicoDAO getDao() {
        if(servicoDao == null){
            servicoDao = new ServicoDAO();
        }
        return servicoDao;
    }

    @Override
    public Servico criarNovaEntidade() {
        return new Servico();
    }
    
}
