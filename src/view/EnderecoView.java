/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CidadeController;
import java.util.Scanner;
import controller.EnderecoController;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Endereco;

/**
 *
 * @author asinf
 */
public class EnderecoView {
    private EnderecoController enderecoController;
    private Scanner sc;

    public EnderecoView() {
        enderecoController = new EnderecoController();
        sc = new Scanner(System.in);
    }

    public void cadastrarEndereco() {
        try {
            System.out.println("Cadastrar Endereço");
            System.out.println("Tipo Logradouro: ");
            enderecoController.getEndereco().setTipoLogradouro(sc.nextLine());
            System.out.println("Nome Logradouro: ");
            enderecoController.getEndereco().setNomeLogradouro(sc.nextLine());
            System.out.println("Número de Referência: ");
            enderecoController.getEndereco().setNumero(sc.nextInt());
            
            CidadeView cidadeView = new CidadeView();
            cidadeView.listarCidades();
            System.out.println("Entre com o ID da Cidade: ");
            enderecoController.getEndereco().getCidade().setId(sc.nextInt());
            
            CidadeController cidadeController = new CidadeController();
            
            if(cidadeController.consultarCidade(enderecoController.getEndereco().getCidade().getId())){
                if(enderecoController.inserirEndereco())
                System.out.println("Endereço cadastrado com sucesso!");
            }else{
                System.out.println("Não foi possível cadastrar o Endereço!");
                System.out.println("Cidade não encontrada!");
            }
        } catch (Exception ex) {
            System.out.println("Não foi possível cadastrar o Endereço!");
            Logger.getLogger(EnderecoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizarEndereco() {
        try {
            System.out.println("Atualizar Endereço");
            System.out.println("Digite o id do Endereço a ser atualizado: ");
            enderecoController.getEndereco().setId(sc.nextInt());
            sc.nextLine();
            System.out.println("Digite o novo Tipo do Logradouto: ");
            enderecoController.getEndereco().setTipoLogradouro(sc.nextLine());
            System.out.println("Digite o novo Nome do Logradouro: ");
            enderecoController.getEndereco().setNomeLogradouro(sc.nextLine());
            System.out.println("Digite o nome Número de Referência: ");
            enderecoController.getEndereco().setNumero(sc.nextInt());

            CidadeView cidadeView = new CidadeView();
            cidadeView.listarCidades();
            System.out.println("Entre com o ID da Cidade: ");
            enderecoController.getEndereco().getCidade().setId(sc.nextInt());
            
            CidadeController cidadeController = new CidadeController();
            
            if(cidadeController.consultarCidade(enderecoController.getEndereco().getCidade().getId())){
                if(enderecoController.atualizarEndereco())
                System.out.println("O Endereco foi atualizado.");
            }else{
                System.out.println("Não foi possível cadastrar o Endereço!");
                System.out.println("Cidade não encontrada!");
            }
            
        } catch (Exception ex) {
            System.out.println("Não foi possível atualizar o Endereço!");
            Logger.getLogger(EnderecoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removerEndereco() {
        try {
            System.out.println("Remover Endereço");
            System.out.println("Digite o id do Endereço a ser removido: ");
            int id = sc.nextInt();
            if(enderecoController.removerEndereco(id) > 0)
                System.out.println("O Endereço foi removido.");
            else
                System.out.println("Não existe nenhum Endereço com esse id!");
        } catch (Exception ex) {
            System.out.println("Não foi possível remover o Endereço!");
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarEnderecos(){
        try {
            System.out.println("Listar Enderecos");
            enderecoController.listarEnderecos();
            if(enderecoController.getEnderecos().isEmpty()){
                System.out.println("Não existem Endereços cadastrados!");
            }else{
                for (Endereco endereco : enderecoController.getEnderecos()) {
                    System.out.println("Id " + endereco.getId()
                        + ", Nome Logradouro: " + endereco.getTipoLogradouro() + " " + endereco.getNomeLogradouro()
                        + ", Número: " + endereco.getNumero() + " , " + endereco.getCidade().getNome() + "/" 
                        + endereco.getCidade().getUf().getSigla());
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
            System.out.println("---[Endereço]---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Remover");
            System.out.println("4 - Listar");
            System.out.println("5 - Voltar ao menu anterior");
            op = sc.nextInt();
            sc.nextLine();//(Esse comando evita o pulo do proximo nextLine)
            switch(op){
                case 1:
                    this.cadastrarEndereco();
                    break;
                case 2:
                    this.atualizarEndereco();
                    break;
                case 3:
                    this.removerEndereco();
                    break;
                case 4:
                    this.listarEnderecos();
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
