/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UserDAO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Usuario;

/**
 *
 * @author wtonf
 */
@ManagedBean(name = "LoginMB")
@ViewScoped
public class LoginBean {

    private final UserDAO userDAO = new UserDAO();
    private Usuario user = new Usuario();

    public String send() {
        user = userDAO.getUser(user.getLogin(), user.getPassword());
        if (user == null) {
            user = new Usuario();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not found!", " Login Error!"));
            return null;
        } else {
            return "/index";
        }
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
