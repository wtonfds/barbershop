/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CargoDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Cargo;

/**
 *
 * @author wtonf
 */
@SessionScoped
@ManagedBean
public class CargoBean extends CrudBean<Cargo, CargoDAO>{
    
    private CargoDAO cargoDao;

    @Override
    public CargoDAO getDao() {
        if(cargoDao == null)
            cargoDao = new CargoDAO();
        return cargoDao;
    }

    @Override
    public Cargo criarNovaEntidade() {
        return new Cargo();
    }
    
}
