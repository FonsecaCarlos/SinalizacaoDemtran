/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EnderecoDAO;
import java.util.ArrayList;
import java.util.List;
import model.Endereco;
import model.Usuario;

/**
 *
 * @author asinf
 */
public class EnderecoController {
    private Endereco endereco;
    private List<Endereco> enderecos;
    private EnderecoDAO dao;

    public EnderecoController() {
        dao = new EnderecoDAO();
        novo();
    }

    public boolean inserirEndereco() throws Exception {
        return dao.add(endereco);
    }
    
    private void novo() {
        endereco = new Endereco();
        enderecos = new ArrayList<>();
    }
    
    public boolean atualizarEndereco() throws Exception {
        return dao.update(endereco);
    }

    public int removerEndereco(int id) throws Exception {
        return dao.delete(id);
    }

    public void listarEnderecos() throws Exception {
        enderecos = dao.findAll();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    /*public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }*/

    public List<Endereco> getEnderecos() {
        return enderecos;
    }
    
    public boolean consultarEndereco(int id)throws Exception{
        if(dao.returnEndereco(id)!=null){
            return true;
        }else{
            return false;
        }
    }
}
