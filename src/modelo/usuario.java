
package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class usuario {
    
    private LongProperty id_usuario;
    private StringProperty nombre_usuario;
    private StringProperty correo_usuario;
    private StringProperty contrasena_usuario;
    
    private static usuario usr;

    public usuario(Long id_usr, String nombre_usr, String correo, String contrasena) {
        this.id_usuario = new SimpleLongProperty(id_usr);
        this.nombre_usuario = new SimpleStringProperty(nombre_usr);
        this.correo_usuario = new SimpleStringProperty(correo);
        this.contrasena_usuario = new SimpleStringProperty(contrasena);
    }
    
    public static usuario getInstanceUser(long id, String nombreusr, String correo, String contrasena){
        if(usr == null){
            usr = new usuario(id,nombreusr,correo,contrasena); 
        }
        return usr;
    }

    public long getId_usuario() {
        return id_usuario.get();
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = new SimpleLongProperty(id_usuario);
    }

    public String getNombre_usuario() {
        return nombre_usuario.get();
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = new SimpleStringProperty(nombre_usuario);
    }

    public String getCorreo_usuario() {
        return correo_usuario.get();
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = new SimpleStringProperty(correo_usuario);
    }

    public String getContrasena_usuario() {
        return contrasena_usuario.get();
    }

    public void setContrasena_usuario(String contrasena_usuario) {
        this.contrasena_usuario = new SimpleStringProperty(contrasena_usuario);
    }
    
    
    
    
    
    
}
