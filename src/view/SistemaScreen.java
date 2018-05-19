/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    foncecacarlos@hotmail.com
 */
package view;

import controller.UsuarioController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author beatr
 */
public class SistemaScreen extends Screen{
    UsuarioController usuarioController = new UsuarioController();
    JButton listarAtividadesAbertas, listarAtividadesConcluidas, listarAtividadesCanceladas, listarAtividadesEndereco, 
            listarAtividadesData;
    JButton adicionarTarefa;
    JLabel titulo, status, endereco, descricao, dataAbertura, dataFechamento, foto, editar, cancelar, concluir;
    
    public SistemaScreen(String titulo, int width, int height, UsuarioController usuarioController) {
        super(titulo, width, height);
        this.usuarioController = usuarioController;
        /*int x, y;
        x=0;
        y=0;*/
        GridBagConstraints grid = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        
        grid.gridx=4;
        grid.gridy=0;
        grid.gridwidth=9;
        grid.fill=GridBagConstraints.BOTH;
        grid.gridwidth = GridBagConstraints.REMAINDER;
        
        this.titulo = new JLabel("Gerênciador de tarefas - Sinalizações DEMTRAN");
        this.titulo.setFont(new Font("Sans-Serif", Font.BOLD, 25));
        this.titulo.setBackground(Color.WHITE);
        this.titulo.setOpaque(true);
        add(this.titulo, grid);
        
        //pula linha
        grid.gridx=0;
        grid.gridy=1;
        grid.gridwidth=15;
        add(new JLabel(" "), grid);
        //----------------------
        
        grid.gridx=0;
        grid.gridy=2;
        grid.gridwidth=3;
        
        listarAtividadesAbertas = new JButton("Listar Atividades Abertas");
        add(listarAtividadesAbertas, grid);
        
        grid.gridx=3;
        listarAtividadesConcluidas = new JButton("Listar Atividades Concluídas");
        add(listarAtividadesConcluidas, grid);
        
        grid.gridx=6;
        listarAtividadesCanceladas = new JButton("Listar Atividades Canceladas");
        add(listarAtividadesCanceladas, grid);
        
        grid.gridx=9;
        listarAtividadesEndereco = new JButton("Listar Atividades por Endereço");
        add(listarAtividadesEndereco, grid);
        
        grid.gridx=12;
        listarAtividadesData = new JButton("Listar por Data");
        add(listarAtividadesData, grid);
        
        
        //pula linha
        grid.gridx=0;
        grid.gridy=3;
        grid.gridwidth=15;
        add(new JLabel(" "), grid);
        //----------------------
        
        grid.fill=GridBagConstraints.BOTH;
        grid.insets = new Insets(5, 10, 5, 10);
        grid.anchor=GridBagConstraints.CENTER;
        grid.gridwidth=1;
        grid.gridx=0;
        grid.gridy=4;//x=0 e y=4
        status = new JLabel("Status");
        status.setBorder(BorderFactory.createLineBorder(Color.black));
        add(status, grid);
        
        grid.gridwidth=3;
        grid.gridx=1;//x=1 e y=4
        endereco = new JLabel("Endereço");
        endereco.setBorder(BorderFactory.createLineBorder(Color.black));
        add(endereco, grid);
        
        grid.gridx=4;//x=4 e y=4
        descricao = new JLabel("Descrição");
        descricao.setBorder(BorderFactory.createLineBorder(Color.black));
        add(descricao, grid);
        
        grid.gridwidth=2;
        grid.gridx=7;//x=7 e y=4
        dataAbertura = new JLabel("Data de Abertura");
        dataAbertura.setBorder(BorderFactory.createLineBorder(Color.black));
        add(dataAbertura, grid);
        
        grid.gridx=9;//x=9 e y=2
        dataFechamento = new JLabel("Data de Fechamento");
        dataFechamento.setBorder(BorderFactory.createLineBorder(Color.black));
        add(dataFechamento, grid);
        
        grid.gridwidth=1;
        grid.gridx=11;//x=11 e y=4
        foto = new JLabel("Foto");
        foto.setBorder(BorderFactory.createLineBorder(Color.black));
        add(foto, grid);
        
        grid.gridx=12;//x=12 e y=4
        editar = new JLabel("Editar");
        editar.setBorder(BorderFactory.createLineBorder(Color.black));
        add(editar, grid);
        
        grid.gridx=13;//x=13 e y=4
        cancelar = new JLabel("Cancelar");
        cancelar.setBorder(BorderFactory.createLineBorder(Color.black));
        add(cancelar, grid);
        
        grid.gridx=14;//x=14 e y=4
        concluir = new JLabel("Concluir");
        concluir.setBorder(BorderFactory.createLineBorder(Color.black));
        add(concluir, grid);
        
        //pula linha
        grid.gridx=0;
        grid.gridy=5;
        grid.gridwidth=15;
        add(new JLabel(" "), grid);
        //----------------------
        
        
        
        
        
        adicionarTarefa = new JButton();
    }
    
}
