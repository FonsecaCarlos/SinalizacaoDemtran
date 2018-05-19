/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

/**
 *
 * @author beatr
 */
public class SplashScreen extends JWindow{
    private int duracao;
    private JProgressBar progressBar;
    private Timer timer;
    private int porcentagem;
    
    public SplashScreen(int duracao) {
        this.duracao = duracao;
    }
    
    // Este é um método simples para mostrar uma tela de apresentção
    // no centro da tela durante a quantidade de tempo passada no construtor

    public void showSplash() {        
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);
        // Configura a posição e o tamanho da janela
        int width = 700;
        int height = 350;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);
        
        // Constrói o splash screen
        //JLabel label = new JLabel(new ImageIcon("splashScreen.jpg"));
        JLabel label = new JLabel("O.S. Sinalizações", JLabel.CENTER);
        JLabel carregando = new JLabel("Carregando ...");
        carregando.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        label.setFont(new Font("Sans-Serif", Font.BOLD, 50));
        content.add(label, BorderLayout.CENTER);
        //carregando.setLocation(30, 500);
        content.add(carregando, BorderLayout.SOUTH);
        content.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 5));     
        
        // Cria o Progess Bar
        content.add(getProgressBar(), BorderLayout.PAGE_END);
        porcentagem = 0;
        timer = new Timer( (duracao/100), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                porcentagem++;
                progressBar.setValue(porcentagem);
                if (porcentagem >= 100) {
                    timer.stop();
                }
            }
        });
        timer.start();
        
        // Torna visível
        setVisible(true);
        
        // Espera ate que os recursos estejam carregados
        try {
            Thread.sleep(duracao);
        } catch (Exception e) {} 
        
        setVisible(false);        
    }
    
    private JProgressBar getProgressBar() {
        if (progressBar == null) {
            progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
        }
        return progressBar;
    }
    
    public void showSplashAndExit() {        
        showSplash();
        this.dispose();
        //System.exit(0);        
    }
}
