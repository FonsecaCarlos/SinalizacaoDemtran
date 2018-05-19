/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CidadeController;
import controller.UfController;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cidade;

/**
 *
 * @author beatr
 */
public class CidadeView {
    
    private CidadeController cidadeController;
    private Scanner sc;

    public CidadeView() {
        this.cidadeController = new CidadeController();
        this.sc = new Scanner(System.in);
    }
    
    public void cadastrarCidade() {
        try {
            System.out.println("Cadastrar Cidade");
            System.out.println("Nome: ");
            //sc.nextLine();//Carlos
            cidadeController.getCidade().setNome(sc.nextLine());
            UfView ufView = new UfView();
            ufView.listarUfs();
            System.out.println("Entre com o ID do Estado");
            cidadeController.getCidade().getUf().setId(sc.nextInt());
            UfController ufController = new UfController();
            
                if(ufController.consultarUf(cidadeController.getCidade().getUf().getId())){
                    if(cidadeController.inserirCidade())
                        System.out.println("Cidade cadastrada com sucesso!");
                }else{
                    System.out.println("Não foi possível cadastrar a cidade!");
                    System.out.println("Estado não encontrado!");
                }
                
        } catch (Exception ex) {
            System.out.println("Não foi possível cadastrar a cidade!");
            Logger.getLogger(CidadeView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizarCidade() {
        try {
            System.out.println("Atualizar Cidade");
            System.out.println("Digite o id da Cidade a ser atualizada: ");
            cidadeController.getCidade().setId(sc.nextInt());
            sc.nextLine();
            System.out.println("Digite o novo Nome da Cidade: ");
            cidadeController.getCidade().setNome(sc.nextLine());
            
            UfView ufView = new UfView();
            ufView.listarUfs();
            
            System.out.println("Entre com o ID do Estado: ");
            cidadeController.getCidade().getUf().setId(sc.nextInt());

            UfController ufController = new UfController();
            
                if(ufController.consultarUf(cidadeController.getCidade().getUf().getId())){
                    if(cidadeController.atualizarCidade())
                        System.out.println("Cidade cadastrada com sucesso!");
                }else{
                    System.out.println("Não foi possível cadastrar a cidade!");
                    System.out.println("Estado não encontrado!");
                }
        } catch (Exception ex) {
            System.out.println("Não foi possível atualizar o Usuario!");
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removerCidade() {
        try {
            System.out.println("Remover Cidade");
            System.out.println("Digite o id da Cidade a ser removida: ");
            int id = sc.nextInt();
            if(cidadeController.removerCidade(id) > 0)
                System.out.println("A Cidade foi removida.");
            else
                System.out.println("Não existe nenhuma Cidade com esse id!");
        } catch (Exception ex) {
            System.out.println("Não foi possível remover a Cidade!");
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarCidades(){
        try {
            System.out.println("Listar Cidades");
            cidadeController.listarCidades();
            if(cidadeController.getCidades().isEmpty()){
                System.out.println("Não existem Cidades cadastradas!");
            }else{
                for (Cidade cidade : cidadeController.getCidades()) {
                    System.out.println("Id " + cidade.getId()
                        + ", Nome: " + cidade.getNome() + ", Estado: "
                        + cidade.getUf().getNome());
                }
            }
        } catch (Exception ex) {
            System.out.println("Não foi possível listar as Cidades!");
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarMenu(){
        int op;
        do{
            System.out.println("---[Cidade]---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Remover");
            System.out.println("4 - Listar");
            System.out.println("5 - Voltar ao menu anterior");
            op = sc.nextInt();
            sc.nextLine();//(Esse comando evita o pulo do proximo nextLine)
            switch(op){
                case 1:
                    this.cadastrarCidade();
                    break;
                case 2:
                    this.atualizarCidade();
                    break;
                case 3:
                    this.removerCidade();
                    break;
                case 4:
                    this.listarCidades();
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
