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
import model.Uf;

/**
 *
 * @author beatr
 */
public class EnderecoDAO {
    
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public EnderecoDAO() {
    }
    
    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }
    
    public boolean add(Endereco endereco) throws Exception {
        try {
            String queryString = "INSERT INTO endereco VALUES(null,?,?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, endereco.getTipoLogradouro());
            ptmt.setString(2, endereco.getNomeLogradouro());
            ptmt.setInt(3, endereco.getNumero());
            ptmt.setInt(4, endereco.getCidade().getId());
            ptmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection, ptmt, resultSet);
        }
    }
    
    public boolean update(Endereco endereco) throws Exception {
        try {
            String queryString = "UPDATE endereco SET tipoLogradouro=?, "
                    + "nomeLogradouro=?, numero=?, cidade=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, endereco.getTipoLogradouro());
            ptmt.setString(2, endereco.getNomeLogradouro());
            ptmt.setInt(3, endereco.getNumero());
            ptmt.setInt(4, endereco.getCidade().getId());
            ptmt.setInt(5, endereco.getId());
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
            String queryString = "DELETE FROM endereco WHERE id=?";
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

    public List<Endereco> findAll() throws Exception {
        List<Endereco> enderecos = new ArrayList<>();
        try {
            String queryString = "SELECT endereco.id, endereco.tipoLogradouro, endereco.nomeLogradouro, endereco.numero, "
                    + "cidade.id AS 'cId', cidade.nome, cidade.uf, uf.nome AS 'estado', uf.sigla "
                    + "FROM endereco, cidade, uf "
                    + "WHERE endereco.cidade=cidade.id and cidade.uf=uf.id";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                enderecos.add(new Endereco(resultSet.getInt("id"), 
                        resultSet.getString("tipoLogradouro"), 
                        resultSet.getString("nomeLogradouro"), 
                        resultSet.getInt("numero"),
                        new Cidade(resultSet.getInt("cId"), resultSet.getString("nome"),
                        new Uf(resultSet.getInt("uf"), resultSet.getString("estado"), resultSet.getString("sigla")) ) ));
            }
            return enderecos;
        } catch (SQLException e) {
            e.printStackTrace();
            return enderecos;
        } finally {
            closeConnection(connection, ptmt, resultSet);
        }
    }
    
    public Endereco returnEndereco(int id) throws Exception {
        Endereco endereco = new Endereco();
        try {
            String queryString = "SELECT endereco.id, endereco.tipoLogradouro, "
                    + "endereco.nomeLogradouro, endereco.numero, "
                    + "cidade.id AS 'cidadeId', cidade.nome, "
                    + "uf.id AS 'ufId', uf.nome AS 'ufNome', uf.sigla "
                    + "FROM endereco, cidade, uf "
                    + "WHERE endereco.cidade=cidade.id and cidade.uf=uf.id and endereco.id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                endereco = new Endereco(resultSet.getInt("id"),resultSet.getString("tipoLogradouro"), 
                        resultSet.getString("nomeLogradouro"), resultSet.getInt("numero"), 
                        new Cidade(resultSet.getInt("cidadeId"), resultSet.getString("nome"), 
                        new Uf(resultSet.getInt("ufId"), resultSet.getString("ufNome"), 
                        resultSet.getString("sigla")) ));
                return endereco;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
