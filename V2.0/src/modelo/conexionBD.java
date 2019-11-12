/*
 * Clase para conectar la base de datos al programma
 * Erick Vega
 * armsystechno@gmail.com
 * SICUH
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author evega
 */
public class conexionBD {
    private Connection conexion;
    private Statement st;
    private final String server = "jdbc:postgresql://localhost:5432/SICUH_USRS";
    private final String usr = "postgres";
    private final String psw = "122512";
            
    
    public conexionBD(){   
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(server,usr,psw);           
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (Exception ex) {  
            Logger.getLogger(conexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public ResultSet CONSULTAR(String sql) throws SQLException{
        return st.executeQuery(sql);     
    }
      
    public int ACTUALIZAR(String sql) throws SQLException{
        return st.executeUpdate(sql);
    }
    public void DESCONECTAR(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
