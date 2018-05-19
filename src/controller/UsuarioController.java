/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author beatr
 */
public class UsuarioController {
    private Usuario usuario;
    private List<Usuario> usuarios;
    private UsuarioDAO dao;

    public UsuarioController() {
        dao = new UsuarioDAO();
        novo();
    }

    public boolean inserirUsuario() throws Exception {
        return dao.add(usuario);
    }
    
    private void novo() {
        usuario = new Usuario();
        usuarios = new ArrayList<>();
    }
    
    public boolean atualizarUsuario() throws Exception {
        return dao.update(usuario);
    }

    public int removerUsuario(int id) throws Exception {
        return dao.delete(id);
    }

    public void listarUsuarios() throws Exception {
        usuarios = dao.findAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
