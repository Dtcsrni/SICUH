/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;



import dao.alumnoDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;



public class InterfazInicioController implements Initializable {
    private ObservableList list=FXCollections.observableArrayList();
    @FXML private ChoiceBox<String> series;
    @FXML TextField txtNombreAlu;
    @FXML TextField txtApellidoPat;
    @FXML TextField txtApellidoMat;
    @FXML TextField txtCorreoAlum;
    @FXML TextField txtID_Tarjeta;
    @FXML Button btnRegistrar;        
    @FXML AnchorPane ap1;
    @FXML Label lblResultAlum;
    
     @FXML private ChoiceBox<String> seriesMod;
    @FXML TextField txtNombreMod;
    @FXML TextField txtApellidoPatMod;
    @FXML TextField txtApellidoMatMod;
    @FXML TextField txtCorreoMod;
    @FXML TextField txtID_TarjetaMod;
    @FXML Button btnBuscarMod;
    @FXML Button btnModifReg;
    @FXML Label lblMod;
    
    private alumnoDAO aDAO = new alumnoDAO();
    
    private void loadData(){
        list.removeAll(list);
        String a = "Ingeniería en Sistemas";
        String b = "Psicología";
        String c = "Derecho";
        String d = "Educación";
        list.addAll(a,b,c,d);
        series.getItems().addAll(list);
        seriesMod.getItems().addAll(list);
    }
    @FXML
    void validarCargarAlumno(ActionEvent evt){
        String Nombre=txtNombreAlu.getText().trim();
        String ApellidoPat=txtApellidoPat.getText().trim();
        String ApellidoMat=txtApellidoMat.getText().trim();
        String CorreoAlum=txtCorreoAlum.getText().trim();
        String CarreraAlum=series.getValue();
        String ID_Tarjeta=txtID_Tarjeta.getText().trim();
        
        
        boolean resultado;
        
        boolean valido;
        valido = Nombre.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
        valido = ApellidoPat.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
        valido = ApellidoMat.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
        valido = CorreoAlum.matches("^[a-zA-Z@]+(?:[\\s.]+[a-zA-Z]+)*$");
        if(CarreraAlum==null)
        valido=false;
        valido = ID_Tarjeta.matches("^[0-9]+");
        
        if(!valido){
            lblResultAlum.setText("Campos incorrectos. Favor de verificar");
            lblResultAlum.setTextFill(Color.web("#b32428"));
        }
        else{
          resultado=aDAO.registrarAlumno(ID_Tarjeta, Nombre, ApellidoPat, ApellidoMat, CorreoAlum, CarreraAlum);
          if(resultado){        
           lblResultAlum.setText("ALUMNO "+Nombre+" "+ApellidoPat+" "+ApellidoMat+" REGISTRADO CORRECTAMENTE");
           lblResultAlum.setTextFill(Color.web("#00E500"));

           txtNombreAlu.setText("");
           txtApellidoPat.setText("");
           txtApellidoMat.setText("");
           txtCorreoAlum.setText("");
           series.setValue(null);
           txtID_Tarjeta.setText("");


          }
        }
        
    }
        @FXML
        void BuscarMod(ActionEvent evt){
            boolean q;
            String ID_Tarjeta=txtID_TarjetaMod.getText().trim();
            q= aDAO.buscarAlumno(ID_Tarjeta);
            if(q){
                txtNombreMod.setText(modelo.alumno.getInstanceUser(null, null, null, null, null, null).getNombreAlumno());
                txtApellidoPatMod.setText(modelo.alumno.getInstanceUser(null, null, null, null, null, null).getApellidoPaterno());
                
                txtApellidoMatMod.setText(modelo.alumno.getInstanceUser(null, null, null, null, null, null).getApellidoMaterno());
                
                txtCorreoMod.setText(modelo.alumno.getInstanceUser(null, null, null, null, null, null).getCorreo());
                
                seriesMod.setValue(modelo.alumno.getInstanceUser(null, null, null, null, null, null).getProgramaEstudios());
                lblMod.setText("Alumno encontrado");
                lblMod.setTextFill(Color.web("#00E500"));
  
            }
            else{
                lblMod.setText("No se ha encontrado el id. Favor de verificar");
                lblMod.setTextFill(Color.web("#b32428"));
            }
        }
        @FXML
        void validarModificarAlumno(ActionEvent evt){
               
        String Nombre=txtNombreMod.getText().trim();
        String ApellidoPat=txtApellidoPatMod.getText().trim();
        String ApellidoMat=txtApellidoMatMod.getText().trim();
        String CorreoAlum=txtCorreoMod.getText().trim();
        String CarreraAlum=seriesMod.getValue();
        String ID_Tarjeta=txtID_TarjetaMod.getText().trim();
        
        
        boolean resultado;
        
        boolean valido;
        valido = Nombre.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
        valido = ApellidoPat.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
        valido = ApellidoMat.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
        valido = CorreoAlum.matches("^[a-zA-Z@]+(?:[\\s.]+[a-zA-Z]+)*$");
        if(CarreraAlum==null)
        valido=false;
        valido = ID_Tarjeta.matches("^[0-9]+");
        
        if(!valido){
            lblMod.setText("Campos incorrectos. Favor de verificar");
            lblMod.setTextFill(Color.web("#b32428"));
        }
        else{
          resultado=aDAO.actualizarAlumno(ID_Tarjeta, Nombre, ApellidoPat, ApellidoMat, CorreoAlum, CarreraAlum);
          if(resultado){        
           lblMod.setText("ALUMNO "+Nombre+" "+ApellidoPat+" "+ApellidoMat+" ACTUALIZADO CORRECTAMENTE");
           lblMod.setTextFill(Color.web("#00E500"));
          }
        }
        
    }
    
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      loadData();
        
    }   
    
    
    
}
