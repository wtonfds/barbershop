/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UserDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Usuario;

/**
 *
 * @author wtonf
 */
@SessionScoped
@ManagedBean
public class UserBean extends CrudBean<Usuario, UserDAO> {

    UserDAO userDao = new UserDAO();

    @Override
    public UserDAO getDao() {
        if (userDao == null) {
            userDao = new UserDAO();
        }
        return userDao;
    }

    @Override
    public Usuario criarNovaEntidade() {
        return new Usuario();
    }

    public List<String> listarAll() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT nome FROM cargo");
        return (List<String>) q.getResultList();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("barberShop");
        return em.createEntityManager();
    }

}
