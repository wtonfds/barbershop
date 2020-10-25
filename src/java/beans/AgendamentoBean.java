/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AgendamentoDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

}
