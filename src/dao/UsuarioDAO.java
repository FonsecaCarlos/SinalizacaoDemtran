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
import model.Usuario;

/**
 *
 * @author beatr
 */
public class UsuarioDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public UsuarioDAO() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }
    
    public boolean add(Usuario usuario) throws Exception {
        try {
            String queryString = "INSERT INTO usuario VALUES(null,?,?,?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, usuario.getNome());
            ptmt.setString(2, usuario.getCpf());
            ptmt.setString(3, usuario.getFuncao());
            ptmt.setString(4, usuario.getEmail());
            ptmt.setString(5, usuario.getSenha());
            ptmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection, ptmt, resultSet);
        }
    }
    
    public boolean update(Usuario usuario) throws Exception {
        try {
            String queryString = "UPDATE usuario SET nome=?, cpf=?, funcao=?, email=?, senha=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, usuario.getNome());
            ptmt.setString(2, usuario.getCpf());
            ptmt.setString(3, usuario.getFuncao());
            ptmt.setString(4, usuario.getEmail());
            ptmt.setString(5, usuario.getSenha());
            ptmt.setInt(4, usuario.getId());
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
            String queryString = "DELETE FROM usuario WHERE id=?";
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

    public List<Usuario> findAll() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String queryString = "SELECT * FROM usuario";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                usuarios.add(new Usuario(resultSet.getInt("id"), 
                        resultSet.getString("nome"), 
                        resultSet.getString("cpf"), 
                        resultSet.getString("funcao"),
                        resultSet.getString("email"),
                        resultSet.getString("senha")));
            }
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
            return usuarios;
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
