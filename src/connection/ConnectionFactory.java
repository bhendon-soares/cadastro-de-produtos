package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Bhendon Soares
 */
public class ConnectionFactory {
    
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/sistema";
    
    public static Connection getConnection(){
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex){
            throw new RuntimeException("Erro ao se conectar com o banco: " + ex);
        }
    }
    
    public static void closeConnection(Connection con){
        try{
            if(con != null){
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement pst){
        closeConnection(con);
        
        try{
            if(pst != null){
                pst.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement pst, ResultSet rs){
        closeConnection(con, pst);
        
        try{
            if(rs != null){
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
