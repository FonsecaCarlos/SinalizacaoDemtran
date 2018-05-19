/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
import controller.UsuarioController;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Usuario;

/**
 *
 * @author beatr
 */
public class LoginScreen extends Screen implements ActionListener{
    private UsuarioController usuarioController;
    private String user;
    private String passwd;
    JLabel titulo, email, senha, esqueci, error;
    JButton entrar;
    JTextField emailField, senhaField;
    
    public LoginScreen(String nome) {
        super(nome, 900, 450);
        user = "";
        passwd = "";
        setResizable(false);
        
        titulo = new JLabel();
        titulo.setText("O.S. Sinalizações");
        titulo.setBounds(150, 25, 600, 90);
        titulo.setFont(new Font("Sans-Serif", Font.BOLD, 70));
        titulo.setBackground(Color.WHITE);
        titulo.setOpaque(true);
        
        email = new JLabel();
        email.setText("Email: ");
        email.setBounds(250, 200, 80, 25);
        emailField = new JTextField();
        emailField.setBounds(330, 200, 240, 25);
        //emailField.setPreferredSize(new Dimension(300, 30));
        
        
        senha = new JLabel();
        senha.setText("Senha: ");
        senha.setBounds(250, 225, 80, 25);
        senhaField = new JPasswordField();
        senhaField.setBounds(330, 225, 240, 25);
        
        esqueci = new JLabel();
        esqueci.setText("Esqueci minha senha");
        esqueci.setFont(new Font("Sans-Serif", Font.ITALIC, 9));
        esqueci.setBounds(330, 250, 240, 20);
        
        entrar = new JButton();
        entrar.setText("Entrar");
        entrar.setBounds(400, 300, 80, 25);
        entrar.setEnabled(true);
        entrar.addActionListener(this);
        
        error = new JLabel();
        error.setText("Email ou senha incorretos");
        error.setBounds(370, 350, 240, 25);
        error.setForeground(Color.RED);
        error.setVisible(false);
        
        getContentPane().add(titulo);
        getContentPane().add(email);
        getContentPane().add(emailField);
        getContentPane().add(senha);
        getContentPane().add(senhaField);
        getContentPane().add(esqueci);
        getContentPane().add(entrar);
        getContentPane().add(error);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == entrar){
            user = emailField.getText();
            passwd = senhaField.getText();
            //emailField.setText("");
            senhaField.setText("");
            //System.out.println(user + " : " + passwd);
            usuarioController = new UsuarioController();
            try {
                usuarioController.listarUsuarios();
                if(usuarioController.getUsuarios().isEmpty()){
                    System.out.println("Não existem Usuários cadastrados!");
                }else{
                    for (Usuario usuario : usuarioController.getUsuarios()) {
                        if( (user.equals(usuario.getEmail())) && (passwd.equals(usuario.getSenha())) ){
                            error.setVisible(false);
                            setVisible(false);
                            //SistemaScreen sistema = new SistemaScreen("Gerênciador de Tarefas", 1200, 600, getUsuarioController());
                            //sistema.showScreen();
                            this.dispose();
                        }else{
                            //Email ou senha incorretos
                            //System.out.println("Email ou senha incorretos.");
                            error.setVisible(true);
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Não foi possível listar os Usuarios!");
                Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }
}