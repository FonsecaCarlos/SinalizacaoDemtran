/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UfDAO;
import java.util.ArrayList;
import java.util.List;
import model.Uf;

/**
 *
 * @author beatr
 */
public class UfController {
    private Uf uf;
    private List<Uf> ufs;
    private UfDAO dao;

    public UfController() {
        dao = new UfDAO();
        novo();
    }

    public boolean inserirUf() throws Exception {
        return dao.add(uf);
    }
    
    public void listarUfs() throws Exception {
        ufs = dao.findAll();
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    private void novo() {
        uf = new Uf();
        ufs = new ArrayList<>();
    }
    
     public boolean atualizarUf() throws Exception {
        return dao.update(uf);
    }

    public List<Uf> getUfs() {
        return ufs;
    }
    
    public int removerUf(int id) throws Exception {
        return dao.delete(id);
    }
    
    public boolean consultarUf(int id)throws Exception{
        if(dao.returnUf(id)!=null){
            return true;
        }else{
            return false;
        }
    }
}
