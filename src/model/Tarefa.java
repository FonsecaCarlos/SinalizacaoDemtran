/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author asinf
 */
public class Tarefa {
    private int id;
    private String atividade;
    private Calendar dataAbertura;
    private Calendar dataFechamento;
    private Byte foto;
    private Endereco endereco;
    private Usuario usuario;

    public Tarefa(int id, String atividade, Calendar dataAbertura, Calendar dataFechamento, Byte foto, Endereco endereco, Usuario usuario) {
        this.id = id;
        this.atividade = atividade;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.foto = foto;
        this.endereco = endereco;
        this.usuario = usuario;
    }

    public Tarefa() {
        this.endereco = new Endereco();
        this.usuario = new Usuario();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public Calendar getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Calendar dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Calendar getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Calendar dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Byte getFoto() {
        return foto;
    }

    public void setFoto(Byte foto) {
        this.foto = foto;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
