/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.faces.application.FacesMessage;
import dao.CrudDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author wtonf
 * @param <E>
 * @param <D>
 * 
 */
public abstract class CrudBean <E, D extends CrudDao>{
    private String screenState = "buscar";
    private E entidade;
    private List<E> entidades;
    private List<String> itens;
    
    
    public void buscar(){
        if(!isBusca()){
            changeScreenBusca();
        }
        try {
            entidades = getDao().buscar();
            if(entidades == null || entidades.size() < 1)
                adicionarMensagem("Não há nenhuma busca", FacesMessage.SEVERITY_WARN);
        } catch (Exception e) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, e);
            adicionarMensagem("Erro ao Buscar!", FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void novo(){
        entidade = criarNovaEntidade();
        changeScreenInsere();
    }
    public void salvar(){
        try {
            getDao().salvar(entidade);
            entidade = criarNovaEntidade();
            adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            changeScreenBusca();
        } catch (Exception e) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, e);
            adicionarMensagem("Erro ao Salvar!", FacesMessage.SEVERITY_ERROR);
        }
    }
    public void editar(E entidade){
        this.entidade = entidade;
        changeScreenEdita();
    }
    public void deletar(E entidade){
        try {
            getDao().deletar(entidade);
            entidades.remove(entidade);
            adicionarMensagem("Deletado com sucesso", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, e);
            adicionarMensagem("Erro ao Deletar", FacesMessage.SEVERITY_ERROR);
        }
        
    }
    public void adicionarMensagem(String mensage, FacesMessage.Severity tipoErro){
        FacesMessage fmg = new FacesMessage(tipoErro, mensage, null);
        FacesContext.getCurrentInstance().addMessage(null, fmg);
    }
    
    public abstract D getDao();
    public abstract E criarNovaEntidade();

    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }
    
    
    public boolean isInsere(){
      return "inserir".equals(screenState);
    }
    
    public boolean isEdita(){
      return "editar".equals(screenState);
    }
    
    public boolean isBusca(){
      return "buscar".equals(screenState);
    }
    
    public void changeScreenInsere(){
        screenState = "inserir";
    }
    
     public void changeScreenEdita(){
        screenState = "editar";
    }
     
      public void changeScreenBusca(){
        screenState = "buscar";
    }
    
}
