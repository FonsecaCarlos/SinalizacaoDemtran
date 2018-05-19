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
import model.Uf;

/**
 *
 * @author beatr
 */
public class UfDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public UfDAO() {
    }
    
    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }
    
    public boolean add(Uf uf) throws Exception {
        try {
            String queryString = "INSERT INTO uf VALUES(null, ?, ?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, uf.getNome());
            ptmt.setString(2, uf.getSigla());
            ptmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection, ptmt, resultSet);
        }
    }
    
    public boolean update(Uf uf) throws Exception {
        try {
            String queryString = "UPDATE uf SET nome=?, sigla=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, uf.getNome());
            ptmt.setString(2, uf.getSigla());
            ptmt.setInt(3, uf.getId());
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
            String queryString = "DELETE FROM uf WHERE id=?";
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
    
    
    public List<Uf> findAll() throws Exception {
        List<Uf> ufs = new ArrayList<>();
        try {
            String queryString = "SELECT * FROM uf";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                ufs.add(new Uf(resultSet.getInt("id"), 
                        resultSet.getString("nome"),
                        resultSet.getString("sigla")));
            }
            return ufs;
        } catch (SQLException e) {
            e.printStackTrace();
            return ufs;
        } finally {
            closeConnection(connection, ptmt, resultSet);
        }
    }
    
    public Uf returnUf(int id) throws Exception {
        Uf uf = new Uf();
        try {
            String queryString = "SELECT * FROM uf WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                uf = new Uf(resultSet.getInt("id"), 
                        resultSet.getString("nome"),
                        resultSet.getString("sigla"));
                return uf;
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
