/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.UsuarioController;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;

/**
 *
 * @author beatr
 */
public class UsuarioView {
    
    private UsuarioController usuarioController;
    private Scanner sc;

    public UsuarioView() {
        usuarioController = new UsuarioController();
        sc = new Scanner(System.in);
    }

    public void cadastrarUsuario() {
        try {
            System.out.println("Cadastrar Usuario");
            System.out.println("Nome: ");
            //sc.nextLine();//Carlos
            usuarioController.getUsuario().setNome(sc.nextLine());
            System.out.println("CPF: ");
            usuarioController.getUsuario().setCpf(sc.nextLine());
            System.out.println("Função: ");
            usuarioController.getUsuario().setFuncao(sc.nextLine());

            if(usuarioController.inserirUsuario())
                System.out.println("Usuario cadastrado com sucesso!");
        } catch (Exception ex) {
            System.out.println("Não foi possível cadastrar o Usuario!");
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizarUsuario() {
        try {
            System.out.println("Atualizar Usuario");
            System.out.println("Digite o id do Usuario a ser atualizado: ");
            usuarioController.getUsuario().setId(sc.nextInt());
            sc.nextLine();
            System.out.println("Digite o novo Nome: ");
            usuarioController.getUsuario().setNome(sc.nextLine());
            System.out.println("Digite o novo CPF: ");
            usuarioController.getUsuario().setCpf(sc.nextLine());
            System.out.println("Digite a nova Função: ");
            usuarioController.getUsuario().setFuncao(sc.nextLine());

            if(usuarioController.atualizarUsuario())
                System.out.println("O Usuario foi atualizado.");
        } catch (Exception ex) {
            System.out.println("Não foi possível atualizar o Usuario!");
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removerUsuario() {
        try {
            System.out.println("Remover Usuario");
            System.out.println("Digite o id do Usuario a ser removido: ");
            int id = sc.nextInt();
            if(usuarioController.removerUsuario(id) > 0)
                System.out.println("O Usuario foi removido.");
            else
                System.out.println("Não existe nenhum Usuario com esse id!");
        } catch (Exception ex) {
            System.out.println("Não foi possível remover o Usuario!");
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarUsuarios(){
        try {
            System.out.println("Listar Usuários");
            usuarioController.listarUsuarios();
            if(usuarioController.getUsuarios().isEmpty()){
                System.out.println("Não existem Usuários cadastrados!");
            }else{
                for (Usuario usuario : usuarioController.getUsuarios()) {
                    System.out.println("Id " + usuario.getId()
                        + ", Nome: " + usuario.getNome() + ", CPF: "
                        + usuario.getCpf() + ", Função: "
                        + usuario.getFuncao());
                }
            }
        } catch (Exception ex) {
            System.out.println("Não foi possível listar os Usuarios!");
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void mostrarMenu(){
        int op;
        do{
            System.out.println("---[Usuário]---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Remover");
            System.out.println("4 - Listar");
            System.out.println("5 - Voltar ao menu anterior");
            op = sc.nextInt();
            sc.nextLine();//(Esse comando evita o pulo do proximo nextLine)
            switch(op){
                case 1:
                    this.cadastrarUsuario();
                    break;
                case 2:
                    this.atualizarUsuario();
                    break;
                case 3:
                    this.removerUsuario();
                    break;
                case 4:
                    this.listarUsuarios();
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
