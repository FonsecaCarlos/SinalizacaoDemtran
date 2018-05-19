/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 setBounds(x, y);   => define uma tamanho fixo para um componente
 */
public class Screen extends JFrame {

    public Screen(String titulo, int width, int height){
        setLayout(null);
        setLocationRelativeTo(null);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);
        
        setTitle("Autenticação:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(255,255,255));
    }
    
    public void showScreen(){
        //pack();
        setVisible(true);
    }
}
