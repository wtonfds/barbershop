/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import components.SessionContext;
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

    // private Integer userId;
    private final UserDAO userDAO = new UserDAO();
    private Usuario user = new Usuario();
    

    public String send() {
        user = userDAO.getUser(user.getLogin(), user.getPassword());
        if (user == null) {
            user = new Usuario();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", " Erro ao Logar!"));
            return null;
        } else {
            SessionContext.getInstance().setAttribute("usuarioLogado", user);
            return "/agendamento_page?faces-redirect=true";
        }
    }
    
    public Usuario getUserName(){
        Usuario userName = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
        return userName;
    }
    
    public Usuario getUserLogado() {
       return (Usuario) SessionContext.getInstance().getUsuarioLogado();
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
