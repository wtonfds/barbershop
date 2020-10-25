/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author wtonf
 */
@Entity
public class Agendamento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAgendamento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date horaAgendamento;
    private String servico;

    public Agendamento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return dataAgendamento;
    }

    public void setData(Date data) {
        this.dataAgendamento = data;
    }

    public Date getHora() {
        return horaAgendamento;
    }

    public void setHora(Date hora) {
        this.horaAgendamento = hora;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

 
}
