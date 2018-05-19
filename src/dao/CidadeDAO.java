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
import model.Uf;

/**
 *
 * @author asinf
 */
public class CidadeDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public CidadeDAO() {
    }
    
    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }
    
    public boolean add(Cidade cidade) throws Exception{
        try {
            String queryString = "INSERT INTO cidade VALUES(null, ?, ?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, cidade.getNome());
            ptmt.setInt(2, cidade.getUf().getId());
            ptmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection, ptmt, resultSet);
        }
    }
    
    public boolean update(Cidade cidade) throws Exception {
        try {
            String queryString = "UPDATE cidade SET nome=?, uf=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, cidade.getNome());
            ptmt.setInt(2, cidade.getUf().getId());
            ptmt.setInt(3, cidade.getId());
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
            String queryString = "DELETE FROM cidade WHERE id=?";
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
    
    public List<Cidade> findAll() throws Exception {
        List<Cidade> cidades = new ArrayList<>();
        try {
            String queryString = "SELECT cidade.id, cidade.nome, uf.id AS 'ufID', uf.nome AS 'uf', uf.sigla AS 'sigla' "
                    + "FROM cidade, uf WHERE cidade.uf=uf.id";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                cidades.add(new Cidade(resultSet.getInt("id"), 
                        resultSet.getString("nome"), new Uf(resultSet.getInt("ufID"), resultSet.getString("uf"),
                        resultSet.getString("sigla"))));
            }
            return cidades;
        } catch (SQLException e) {
            e.printStackTrace();
            return cidades;
        } finally {
            closeConnection(connection, ptmt, resultSet);
        }
    }
    
    public Cidade returnCidade(int id) throws Exception {
        Cidade cidade = new Cidade();
        try {
            String queryString = "SELECT cidade.id AS 'id', cidade.nome AS 'nome', cidade.uf AS 'uf', uf.nome AS 'estado', uf.sigla AS 'sigla'"
                    + "FROM cidade, uf WHERE cidade.uf=uf.id and cidade.id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                cidade = new Cidade(resultSet.getInt("id"),resultSet.getString("nome"),
                        new Uf(resultSet.getInt("uf"), resultSet.getString("estado"), resultSet.getString("sigla")));
                return cidade;
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
