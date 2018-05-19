/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.UfController;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Uf;

/**
 *
 * @author beatr
 */
public class UfView {
    private UfController ufController;
    private Scanner sc;

    public UfView() {
        ufController = new UfController();
        sc = new Scanner(System.in);
    }
    
    public void cadastrarUf() {
        try {
            System.out.println("Cadastrar Estado");
            System.out.println("Nome: ");
            //sc.nextLine();//Carlos
            ufController.getUf().setNome(sc.nextLine());
            
            System.out.println("Sigla: ");
            ufController.getUf().setSigla(sc.nextLine());
            
            if(ufController.inserirUf())
                System.out.println("Estado cadastrado com sucesso!");
        } catch (Exception ex) {
            System.out.println("Não foi possível cadastrar o Estado!");
            Logger.getLogger(UfView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizarUf() {
        try {
            System.out.println("Atualizar Estado");
            System.out.println("Digite o id do Estado a ser atualizado: ");
            ufController.getUf().setId(sc.nextInt());
            sc.nextLine();
            System.out.println("Digite o Estado: ");
            ufController.getUf().setNome(sc.nextLine());
            System.out.println("Digite a Sigla: ");
            ufController.getUf().setSigla(sc.nextLine());
            
            if(ufController.atualizarUf())
                System.out.println("O Estado foi atualizado.");
        } catch (Exception ex) {
            System.out.println("Não foi possível atualizar o Estado!");
            Logger.getLogger(UfView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removerUf() {
        try {
            System.out.println("Remover Estado");
            System.out.println("Digite o id do Estado a ser removido: ");
            int id = sc.nextInt();
            if(ufController.removerUf(id) > 0)
                System.out.println("O Estado foi removido.");
            else
                System.out.println("Não existe nenhum Estado com esse id!");
        } catch (Exception ex) {
            System.out.println("Não foi possível remover o Estado!");
            Logger.getLogger(UfView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void listarUfs(){
        try {
            System.out.println("Listar Estados");
            ufController.listarUfs();
            if(ufController.getUfs().isEmpty()){
                System.out.println("Não existem Estados cadastrados!");
            }else{
                for (Uf uf : ufController.getUfs()) {
                    System.out.println("Id " + uf.getId()
                        + ", Estado: " + uf.getNome() + 
                          ", Sigla: " + uf.getSigla());
                }
            }
        } catch (Exception ex) {
            System.out.println("Não foi possível listar os Estados!");
            Logger.getLogger(UfView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void mostrarMenu(){
        int op;
        do{
            System.out.println("---[Estado]---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Remover");
            System.out.println("4 - Listar");
            System.out.println("5 - Voltar ao menu anterior");
            op = sc.nextInt();
            sc.nextLine();//(Esse comando evita o pulo do proximo nextLine)
            switch(op){
                case 1:
                    this.cadastrarUf();
                    break;
                case 2:
                    this.atualizarUf();
                    break;
                case 3:
                    this.removerUf();
                    break;
                case 4:
                    this.listarUfs();
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
