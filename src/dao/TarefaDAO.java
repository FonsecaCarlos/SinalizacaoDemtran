/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cidade;
import model.Endereco;
import model.Tarefa;
import model.Uf;
import model.Usuario;

/**
 *
 * @author carlos
 */
public class TarefaDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public TarefaDAO() {
    }
    
    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }
    
    public boolean add(Tarefa tarefa) throws Exception {
        try {
            String queryString = "INSERT INTO tarefa VALUES(null,?,?,?,null,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, tarefa.getAtividade());
            ptmt.setDate(2, null,tarefa.getDataAbertura());
            ptmt.setDate(3, null, tarefa.getDataFechamento());
            //ptmt.setByte(4, tarefa.getFoto());
            //how presist image into database jdbc
            ptmt.setInt(4, tarefa.getEndereco().getId());
            ptmt.setInt(5, tarefa.getUsuario().getId());
            ptmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection, ptmt, resultSet);
        }
    }
    
    public boolean update(Tarefa tarefa) throws Exception {
        try {
            String queryString = "UPDATE tarefa SET atividade=?, "
                    + "endereco=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, tarefa.getAtividade());
            ptmt.setInt(2, tarefa.getEndereco().getId());
            ptmt.setInt(3, tarefa.getId());
            ptmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection, ptmt, resultSet);
        }
    }

    public int delete(int id) throws Exception {
        try {
            String queryString = "DELETE FROM tarefa WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            int val = ptmt.executeUpdate();
            
            return val;
        } catch (SQLException e) {            
            e.printStackTrace();
            return -1;
        } finally {
            closeConnection(connection, ptmt, resultSet);
        }
    }

    public List<Tarefa> findAll() throws Exception {
        List<Tarefa> tarefas = new ArrayList<>();
        try {
            String queryString = "SELECT tarefa.id, tarefa.atividade, "
                    + "tarefa.foto, endereco.id AS 'enderecoId', endereco.tipoLogradouro, "
                    + "endereco.nomeLogradouro, endereco.numero, "
                    + "cidade.id AS 'cidadeId', cidade.nome, "
                    + "uf.id AS 'ufId', uf.nome AS 'ufNome', uf.sigla, "
                    + "usuario.id AS 'usuarioId', usuario.nome AS 'usuarioNome', usuario.cpf, "
                    + "usuario.funcao "
                    + "FROM tarefa, endereco, cidade, uf, usuario "
                    + "WHERE tarefa.usuario=usuario.id and tarefa.endereco=endereco.id and "
                    + "endereco.cidade=cidade.id and cidade.uf=uf.id";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                tarefas.add(new Tarefa(resultSet.getInt("id"), resultSet.getString("atividade"), 
                        null, null, resultSet.getByte("foto"), 
                        new Endereco(resultSet.getInt("enderecoId"), 
                            resultSet.getString("tipoLogradouro"), 
                            resultSet.getString("nomeLogradouro"), 
                            resultSet.getInt("numero"),
                                new Cidade(resultSet.getInt("cidadeId"), resultSet.getString("nome"),
                                    new Uf(resultSet.getInt("ufId"), resultSet.getString("ufNome"), resultSet.getString("sigla")))),
                        new Usuario(resultSet.getInt("usuarioId"), resultSet.getString("usuarioNome"),
                            resultSet.getString("cpf"), resultSet.getString("funcao"), resultSet.getString("email"), 
                            resultSet.getString("senha"))));
            }
            return tarefas;
        } catch (SQLException e) {
            e.printStackTrace();
            return tarefas;
        } finally {
            closeConnection(connection, ptmt, resultSet);
        }
    }
    
    
    public void closeConnection(Connection conn, Statement st, ResultSet rs) throws Exception{
        close(conn, st, rs);
    }
    
    public void closeConnection(Connection conn, Statement st) throws Exception{
        close(conn, st, null);
    }
    
    public void closeConnection(Connection conn) throws Exception{
        close(conn, null, null);
    }
    
    public void close(Connection conn, Statement st, ResultSet rs) throws Exception{
        try{
            if(rs != null) rs.close();
            if(st != null) st.close();
            if(conn != null) conn.close();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
