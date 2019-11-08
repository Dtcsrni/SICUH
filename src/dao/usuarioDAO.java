
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import modelo.conexionBD;

public class usuarioDAO {

    public usuarioDAO() {
    }
    
    public boolean login(String correo, String contrasena){
       conexionBD con = new conexionBD();
       
        try {
            ResultSet rs = con.CONSULTAR("SELECT * FROM admon WHERE correo='"+correo+"' AND contrasena='"+contrasena+"'");
            if(rs.next()){
                modelo.usuario usr = modelo.usuario.getInstanceUser(
                        rs.getLong("id_usr"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contrasena") );
                
             return true;
            }
            
        } catch (SQLException ex) {
            Alert a=new Alert(Alert.AlertType.ERROR, "Mensaje");
            a.showAndWait();
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return false;
       
       
    }
            
    
}
