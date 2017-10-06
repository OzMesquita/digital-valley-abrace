package DAO;

import java.sql.*;

public class ConnectionFactory {
    private Connection connection;
    public Connection abreConexao(){
        if(connection != null) fechaConexao();
        try{
            Class.forName("driver");
            String caminhoServidor = "servidor";
            connection = DriverManager.getConnection(caminhoServidor, "", "");
            return connection;
        }catch(SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro com o driver do JDBC");
        }
        return null;
    }
    
    public void fechaConexao(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
               throw new RuntimeException(e);
            }            
        }

    }
}
