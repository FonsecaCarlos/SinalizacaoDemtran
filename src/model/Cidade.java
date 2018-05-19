/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author asinf
 */
public class Cidade {
    private int id;
    private String nome;
    private Uf uf;
    /*Adicionar Uf*/

    public Cidade(int id, String nome, Uf uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }

    public Cidade() {
        this.uf = new Uf();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }
}
