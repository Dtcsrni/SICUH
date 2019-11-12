
package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class alumno {
    private StringProperty id_user;
    private StringProperty nombreAlumno;
    private StringProperty apellidoPaterno;
    private StringProperty apellidoMaterno;
    private StringProperty programaEstudios;
    private StringProperty correo;
    private static alumno usr;

    public alumno(String id_usr,String nombre_usr, String apellido_Paterno, String apellido_Materno, String programa_Estudios, String correo_Alum) {
        this.id_user = new SimpleStringProperty(id_usr);
        this.nombreAlumno = new SimpleStringProperty(nombre_usr);
        this.apellidoPaterno = new SimpleStringProperty(apellido_Paterno);
        this.apellidoMaterno = new SimpleStringProperty(apellido_Materno);
        this.programaEstudios = new SimpleStringProperty(programa_Estudios);
        this.correo = new SimpleStringProperty(correo_Alum);
    }
    
    public static alumno getInstanceUser(String id_usr,String nombre_usr, String apellido_Paterno, String apellido_Materno, String programa_Estudios, String correo_Alum){
        if(usr == null){
            usr = new alumno(id_usr,nombre_usr, apellido_Paterno, apellido_Materno,programa_Estudios,correo_Alum); 
        }
        return usr;
    }

     public String getIdUser() {
        return id_user.get();
    }

    public void setIdUser(String id_usr) {
        this.id_user = new SimpleStringProperty(id_usr);
    }
    
    public String getNombreAlumno() {
        return nombreAlumno.get();
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = new SimpleStringProperty(nombreAlumno);
    }

    public String getApellidoPaterno() {
        return apellidoPaterno.get();
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
    }

    public String getApellidoMaterno() {
        return apellidoMaterno.get();
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
    }

    public String getProgramaEstudios() {
        return programaEstudios.get();
    }

    public void setProgramaEstudios(String programaEstudios) {
        this.programaEstudios = new SimpleStringProperty(programaEstudios);
    }

    public String getCorreo() {
        return correo.get();
    }

    public void setCorreo(String correo) {
        this.correo = new SimpleStringProperty(correo);
    }
    
    
    
    
}
