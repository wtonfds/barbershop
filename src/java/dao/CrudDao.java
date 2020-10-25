/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author wtonf
 * 
 */
public interface CrudDao <E>{
    public void salvar(E entidade);
    public void deletar(E entidade);
    public List <E> buscar();
    
}
