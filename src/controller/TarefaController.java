/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TarefaDAO;
import java.util.ArrayList;
import java.util.List;
import model.Tarefa;

/**
 *
 * @author carlos
 */
public class TarefaController {
    private Tarefa tarefa;
    private List<Tarefa> tarefas;
    private TarefaDAO dao;

    public TarefaController() {
        dao = new TarefaDAO();
        novo();
    }

    public boolean inserirTarefa() throws Exception {
        return dao.add(tarefa);
    }
    
    private void novo() {
        tarefa = new Tarefa();
        tarefas = new ArrayList<>();
    }
    
    public boolean atualizarTarefa() throws Exception {
        return dao.update(tarefa);
    }

    public int removerTarefa(int id) throws Exception {
        return dao.delete(id);
    }

    public void listarTarefas() throws Exception {
        tarefas = dao.findAll();
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    /*public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }*/

    public List<Tarefa> getTarefas() {
        return tarefas;
    }
}
