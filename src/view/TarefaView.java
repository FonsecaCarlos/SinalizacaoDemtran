/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.EnderecoController;
import controller.TarefaController;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tarefa;
import model.Usuario;

/**
 *
 * @author carlos
 */
public class TarefaView {
    private TarefaController tarefaController;
    private Scanner sc;

    public TarefaView(Usuario usuario) {
        tarefaController = new TarefaController();
        sc = new Scanner(System.in);
        tarefaController.getTarefa().setUsuario(usuario);
    }

    public void cadastrarTarefa() {
        try {
            System.out.println("Cadastrar Tarefa");
            System.out.println("Tarefa (Descrição) :");
            tarefaController.getTarefa().setAtividade(sc.nextLine());
            
            //===== Pegar Data automaticamente do sistema
            tarefaController.getTarefa().setDataAbertura(null);
            tarefaController.getTarefa().setDataFechamento(null);
            //===========================================
            
            
            //===== Pega Endereço
            EnderecoView enderecoView = new EnderecoView();
            enderecoView.listarEnderecos();
            System.out.println("Entre com o ID do Endereço: ");
            tarefaController.getTarefa().getEndereco().setId(sc.nextInt());
            
            //===== Foto Opcional
            tarefaController.getTarefa().setFoto(null);
            //===============================================
            
            EnderecoController enderecoController = new EnderecoController();
            
            //===== Pega Usuário
            //Pegar automaticamenteo ID do usuário que está com a sessão aberta
            //tarefaController.getTarefa().setUsuario(new Usuario(1, "Carlos", "12345678990", "Fiscal deTrânsito"));
            //====================================================
            
            if(enderecoController.consultarEndereco( tarefaController.getTarefa().getEndereco().getId()) ){
                if(tarefaController.inserirTarefa())
                    System.out.println("Tarefa cadastrada com sucesso!");
            }else{
                System.out.println("Não foi possível cadastrar a Tarefa!");
                System.out.println("Endereço não encontrada!");
            }
        } catch (Exception ex) {
            System.out.println("Não foi possível cadastrar a Tarefa!");
            Logger.getLogger(TarefaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizarTarefa() {
        try {
            System.out.println("Atualizar Tarefa");
            System.out.println("Digite o id da Tarefa a ser atualizada: ");
            tarefaController.getTarefa().setId(sc.nextInt());
            sc.nextLine();
            
            System.out.println("Tarefa (Descrição) :");
            tarefaController.getTarefa().setAtividade(sc.nextLine());
            
            //===== Pegar Data automaticamente do sistema
            tarefaController.getTarefa().setDataAbertura(null);
            tarefaController.getTarefa().setDataFechamento(null);
            //===========================================
            
            
            //===== Pega Endereço
            EnderecoView enderecoView = new EnderecoView();
            enderecoView.listarEnderecos();
            System.out.println("Entre com o ID do Endereço: ");
            tarefaController.getTarefa().getEndereco().setId(sc.nextInt());
            
            //===== Foto Opcional
            tarefaController.getTarefa().setFoto(null);
            //===============================================
            
            EnderecoController enderecoController = new EnderecoController();
            
            //===== Pega Usuário
            //Pegar automaticamenteo ID do usuário que está com a sessão aberta
            //tarefaController.getTarefa().setUsuario(new Usuario(1, "Carlos", "12345678990", "Fiscal deTrânsito"));
            //====================================================
            
            if(enderecoController.consultarEndereco( tarefaController.getTarefa().getEndereco().getId()) ){
                if(tarefaController.atualizarTarefa())
                    System.out.println("Tarefa atualizada com sucesso!");
            }else{
                System.out.println("Não foi possível atualizar a Tarefa!");
                System.out.println("Endereço não encontrada!");
            }
            
        } catch (Exception ex) {
            System.out.println("Não foi possível atualizar a Tarefa!");
            Logger.getLogger(EnderecoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removerTarefa() {
        try {
            System.out.println("Remover Tarefa");
            System.out.println("Digite o id da Tarefa a ser removido: ");
            int id = sc.nextInt();
            if(tarefaController.removerTarefa(id) > 0)
                System.out.println("A Tarefa foi removida.");
            else
                System.out.println("Não existe nenhuma Tarefa com esse id!");
        } catch (Exception ex) {
            System.out.println("Não foi possível remover a Tarefa!");
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarTarefas(){
        try {
            System.out.println("Listar Tarefas");
            tarefaController.listarTarefas();
            if(tarefaController.getTarefas().isEmpty()){
                System.out.println("Não existem Tarefas cadastradas!");
            }else{
                for (Tarefa tarefa : tarefaController.getTarefas()) {
                    System.out.println("Id " + tarefa.getId()
                        + ", Atividade: " + tarefa.getAtividade()
                        + ", Endereço: " + tarefa.getEndereco().getTipoLogradouro() + " " + tarefa.getEndereco().getNomeLogradouro() 
                        + ", nº " + tarefa.getEndereco().getNumero()
                        + ", " + tarefa.getEndereco().getCidade().getNome() + "/" + tarefa.getEndereco().getCidade().getUf().getSigla()
                        + ", Usuário = " + tarefa.getUsuario().getNome());
                }
            }
        } catch (Exception ex) {
            System.out.println("Não foi possível listar os Enderecos!");
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void mostrarMenu(){
        int op;
        do{
            System.out.println("---[Tarefa]---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Remover");
            System.out.println("4 - Listar");
            System.out.println("5 - Voltar ao menu anterior");
            op = sc.nextInt();
            sc.nextLine();//(Esse comando evita o pulo do proximo nextLine)
            switch(op){
                case 1:
                    this.cadastrarTarefa();
                    break;
                case 2:
                    this.atualizarTarefa();
                    break;
                case 3:
                    this.removerTarefa();
                    break;
                case 4:
                    this.listarTarefas();
                    break;
                case 5:
                    //do nothing
                    break;
                default:
                    System.out.println("Opcão Inválida");
            }
        }while(op != 5);
    }   
}
