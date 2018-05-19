/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sinalizacaodemtran;

import java.util.Scanner;
import model.Usuario;
import view.CidadeView;
import view.EnderecoView;
import view.LoginScreen;
import view.SistemaScreen;
import view.SplashScreen;
import view.TarefaView;
import view.UfView;
import view.UsuarioView;

/**
 *
 * @author asinf
 */
public class SinalizacaoDemtran {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         // Mostra uma imagem com o título da aplicação 
        SplashScreen splash = new SplashScreen(1000);
        splash.showSplashAndExit(); 
        
        LoginScreen login = new LoginScreen("Autenticação:");
        login.showScreen();
        
        SistemaScreen sistema = new SistemaScreen("Gerênciador de Tarefas", 1200, 600, login.getUsuarioController());
        sistema.showScreen();
        
        int op;
        Scanner sc = new Scanner(System.in);
        CidadeView c = new CidadeView();
        UfView e = new UfView();
        UsuarioView u = new UsuarioView();
        EnderecoView end = new EnderecoView();
        TarefaView t = new TarefaView(new Usuario());
        
        do{
            System.out.println("---Sistema---");
            System.out.println("1 - Cidade");
            System.out.println("2 - Estado");
            System.out.println("3 - Usuário");
            System.out.println("4 - Endereço");
            System.out.println("5 - Tarefa");
            System.out.println("0 - Sair");
            op = sc.nextInt();
            switch(op){
                case 0:
                    System.out.println("Saindo do sistema!");
                    break;
                case 1:
                    c.mostrarMenu();
                    break;
                case 2:
                    e.mostrarMenu();
                    break;
                case 3:
                    u.mostrarMenu();
                    break;
                case 4:
                    end.mostrarMenu();
                    break;
                case 5:
                    t.mostrarMenu();
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        }while(op != 0);
    }
}
