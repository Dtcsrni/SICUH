
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import modelo.conexionBD;

public class alumnoDAO {

    public alumnoDAO() {
        
    }
    
    public boolean registrarAlumno(String Id_User, String Nombre,String ApellidoPat,String ApellidoMat,String CorreoAlum,String CarreraAlum){
        conexionBD con = new conexionBD();
        int q;
        try {
            ResultSet rs = con.CONSULTAR("SELECT * FROM alumnos WHERE id_usr='"+Id_User+"' AND Nombre='"+Nombre+"' AND correo='"+CorreoAlum+"'");
            if(rs.next()){
             return false;
            }else{
               q = con.ACTUALIZAR("INSERT INTO alumnos (id_usr,nombre,apellido_paterno,apellido_materno,programa_estudios,correo) VALUES ('"+Id_User+"','"+Nombre+"','"+ApellidoPat+"','"+ApellidoMat+"','"+CarreraAlum+"','"+CorreoAlum+"')");
               if(q==0) 
                 return false;
            }
            
        } catch (SQLException ex) {
            Alert a=new Alert(Alert.AlertType.ERROR, "Mensaje");
            a.showAndWait();
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);    
        }
        
        return true;
    }
    
     public modelo.alumno buscarAlumno(String Id_User){
        conexionBD con = new conexionBD();
         modelo.alumno usr = null;
        int q;
        try {
            ResultSet rs = con.CONSULTAR("SELECT * FROM alumnos WHERE id_usr='"+Id_User+"'");
            if(rs.next()){
               usr = new modelo.alumno(
                        rs.getString("id_usr"),
                        rs.getString("nombre"),
                        rs.getString("apellido_paterno"),
                        rs.getString("apellido_materno"),
                        rs.getString("programa_estudios"),
                        rs.getString("correo") );
                
             return usr;
            }else{
             return null;
            }
            
        } catch (SQLException ex) {
            Alert a=new Alert(Alert.AlertType.ERROR, "Mensaje");
            a.showAndWait();
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);    
        }
        
        return usr;
    }
     
     public boolean actualizarAlumno(String Id_User, String Nombre,String ApellidoPat,String ApellidoMat,String CorreoAlum,String CarreraAlum){
        conexionBD con = new conexionBD();
        int q;
        try {
            ResultSet rs = con.CONSULTAR("SELECT * FROM alumnos WHERE id_usr='"+Id_User+"' AND Nombre='"+Nombre+"' AND correo='"+CorreoAlum+"'");
            if(rs.next()){
             return false;
            }else{
               q = con.ACTUALIZAR("UPDATE alumnos SET id_usr='"+Id_User+"',nombre='"+Nombre+"', apellido_paterno='"+ApellidoPat+"', apellido_materno='"+ApellidoMat+"',correo='"+CorreoAlum+"',programa_estudios='"+CarreraAlum+"' WHERE id_usr='"+Id_User+"'");
               if(q==0) 
                 return false;
            }
            
        } catch (SQLException ex) {
            Alert a=new Alert(Alert.AlertType.ERROR, "Mensaje");
            a.showAndWait();
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);    
        }
        
        return true;
    }
    
}
