/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CidadeDAO;
import java.util.ArrayList;
import java.util.List;
import model.Cidade;

/**
 *
 * @author beatr
 */
public class CidadeController {
    private Cidade cidade;
    private List<Cidade> cidades;
    private CidadeDAO dao;

    public CidadeController() {
        dao = new CidadeDAO();
        novo();
    }
    
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public CidadeDAO getDao() {
        return dao;
    }

    public void setDao(CidadeDAO dao) {
        this.dao = dao;
    }

    public boolean inserirCidade() throws Exception {
        return dao.add(cidade);
    }

    private void novo() {
        cidade = new Cidade();
        cidades = new ArrayList<>();
    }
    
    public void listarCidades() throws Exception {
        cidades = dao.findAll();
    }
    
    public boolean atualizarCidade() throws Exception {
        return dao.update(cidade);
    }
    
    public int removerCidade(int id) throws Exception {
        return dao.delete(id);
    }
    
    public boolean consultarCidade(int id)throws Exception{
        if(dao.returnCidade(id)!=null){
            return true;
        }else{
            return false;
        }
    }
}
