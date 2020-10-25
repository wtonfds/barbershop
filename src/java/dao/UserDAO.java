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
import model.Usuario;


/**
 *
 * @author wtonf
 */
public class UserDAO implements CrudDao<Usuario>{
    @PersistenceContext
    
    
    @Override
    public void salvar(Usuario user) {
        if(user.getId() == null){
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }else{
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deletar(Usuario user) {
        if(user != null){
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createQuery("DELETE usuario from Usuario WHERE id = " + user.getId());
            q.executeUpdate();
            em.getTransaction().commit();
        }else{
            JOptionPane.showConfirmDialog(null, "Erro ao deletar Usuário");
        }
    }

    @Override
    public List<Usuario> buscar() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT u FROM Usuario u ORDER BY u.nome DESC");
        return (List<Usuario>) q.getResultList();
    }
    
    public Usuario getUser(String login, String password) {
        try {
            EntityManager em = getEntityManager();
            Usuario user = (Usuario) em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login and u.password = :password")
                    .setParameter("login", login).setParameter("password", password).getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    private EntityManager getEntityManager() {
        EntityManagerFactory  em = Persistence.createEntityManagerFactory("barberShop");
        return em.createEntityManager();
    }

}