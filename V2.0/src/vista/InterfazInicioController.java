/*
 * Clase para mostrar vista de pantalla principal
 * Erick Vega
 * armsystechno@gmail.com
 * SICUH
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
    
    private void loadData(){//carga las carreras en los choicebox
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
    void validarCargarAlumno(ActionEvent evt){//valida que los campos sean correctos y en caso de serlo, los sube a la BD
        String Nombre=txtNombreAlu.getText().trim();
        String ApellidoPat=txtApellidoPat.getText().trim();
        String ApellidoMat=txtApellidoMat.getText().trim();
        String CorreoAlum=txtCorreoAlum.getText().trim();
        String CarreraAlum=series.getValue();
        String ID_Tarjeta=txtID_Tarjeta.getText().trim();
        
        
        boolean resultado;
        
        boolean valido;
        //Se revisa si el formato de lo ingresado en los campos coincide con lo que debería ir en el campo
        valido = Nombre.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
        valido = ApellidoPat.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
        valido = ApellidoMat.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
        valido = CorreoAlum.matches("^[a-zA-Z@]+(?:[\\s.]+[a-zA-Z]+)*$");
        if(CarreraAlum==null)//si no se ha elegido nada en el choicebox
        valido=false;
        valido = ID_Tarjeta.matches("^[0-9]+");
        
        if(!valido){//Si algo no es válido, se muestra mensaje en rojo
            lblResultAlum.setText("Campos incorrectos. Favor de verificar");
            lblResultAlum.setTextFill(Color.web("#b32428"));
        }
        else{//si todos los campos son correctos, se registra el alumno en la BD y se muestra mensaje en verde
          resultado=aDAO.registrarAlumno(ID_Tarjeta, Nombre, ApellidoPat, ApellidoMat, CorreoAlum, CarreraAlum);
          if(resultado){        
           lblResultAlum.setText("ALUMNO "+Nombre+" "+ApellidoPat+" "+ApellidoMat+" REGISTRADO CORRECTAMENTE");
           lblResultAlum.setTextFill(Color.web("#00E500"));
           //Se limpian los campos una vez se haya subido la info a la BD
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
        void BuscarPersona(ActionEvent evt){
            modelo.alumno q;
            if (evt.getSource().equals(btnBuscarMod)) {
                String ID_TarjetaMod = txtID_TarjetaMod.getText().trim();//se obtiene el valor del campo de texto y se le quitan los posibles espacios extra que tenga

                q = aDAO.buscarAlumno(ID_TarjetaMod);//se busca el id en la BD
                if (q!=null) {//si existe el registro
                    //se activan las cajas de texto y el botón de registro
                    txtNombreMod.setEditable(true);
                    txtNombreMod.setDisable(false);
                    txtApellidoPatMod.setEditable(true);
                    txtApellidoPatMod.setDisable(false);
                    txtApellidoMatMod.setEditable(true);
                    txtApellidoMatMod.setDisable(false);
                    txtCorreoMod.setEditable(true);
                    txtCorreoMod.setDisable(false);
                    seriesMod.setDisable(false);
                    btnModifReg.setDisable(false);
                    //Se llenan los campos de texto con el contenido de la consulta
                    txtNombreMod.setText(q.getNombreAlumno());
                    txtApellidoPatMod.setText(q.getApellidoPaterno());
                    txtApellidoMatMod.setText(q.getApellidoMaterno());
                    txtCorreoMod.setText(q.getCorreo());
                    seriesMod.setValue(q.getProgramaEstudios());
                    lblMod.setText("Alumno encontrado");//Se muestra mensaje en verde
                    lblMod.setTextFill(Color.web("#00E500"));

                } else {//Si no se encontró ningún registro en la BD
                    lblMod.setText("No se ha encontrado el id. Favor de verificar");//Se muestra mensaje en rojo
                    lblMod.setTextFill(Color.web("#b32428"));
                    //Se limpian los campos una vez se haya subido la info a la BD
                    txtNombreMod.setText("");
                    txtApellidoPatMod.setText("");
                    txtApellidoMatMod.setText("");
                    txtCorreoMod.setText("");
                    seriesMod.setValue(null);
                    //y se hacen inaccesibles los campos de modificación
                    txtNombreMod.setEditable(false);
                    txtNombreMod.setDisable(true);
                    txtApellidoPatMod.setEditable(false);
                    txtApellidoPatMod.setDisable(true);
                    txtApellidoMatMod.setEditable(false);
                    txtApellidoMatMod.setDisable(true);
                    txtCorreoMod.setEditable(false);
                    txtCorreoMod.setDisable(true);
                    seriesMod.setDisable(true);
                    btnModifReg.setDisable(true);
                }

            }
        }
        @FXML
        void validarModificar(ActionEvent evt){//se validan los campos, si son válidos, los campos de texto se llenan con el registro encontrado y se activa botón de acción correspondiente
            if (evt.getSource().equals(btnModifReg)) {
                String Nombre = txtNombreMod.getText().trim();
                String ApellidoPat = txtApellidoPatMod.getText().trim();
                String ApellidoMat = txtApellidoMatMod.getText().trim();
                String CorreoAlum = txtCorreoMod.getText().trim();
                String CarreraAlum = seriesMod.getValue();
                String ID_Tarjeta = txtID_TarjetaMod.getText().trim();
                boolean resultado;
                boolean valido;

                valido = Nombre.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
                valido = ApellidoPat.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
                valido = ApellidoMat.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$");
                valido = CorreoAlum.matches("^[a-zA-Z@]+(?:[\\s.]+[a-zA-Z]+)*$");
                if (CarreraAlum == null)
                    valido = false;
                valido = ID_Tarjeta.matches("^[0-9]+");

                if (!valido) {//si el resultado de la validación es inválido
                    lblMod.setText("Campos incorrectos. Favor de verificar");
                    lblMod.setTextFill(Color.web("#b32428"));
                } else {//si es válido
                    resultado = aDAO.actualizarAlumno(ID_Tarjeta, Nombre, ApellidoPat, ApellidoMat, CorreoAlum, CarreraAlum);
                    if (resultado) {
                       txtNombreMod.setText("");
                       txtApellidoPatMod.setText("");
                       txtApellidoMatMod.setText("");
                       txtCorreoMod.setText("");
                       seriesMod.setValue(null);
                       txtID_TarjetaMod.setText("");

                       txtNombreMod.setEditable(false);
                       txtNombreMod.setDisable(true);
                       txtApellidoPatMod.setEditable(false);
                       txtApellidoPatMod.setDisable(true);
                       txtApellidoMatMod.setEditable(false);
                       txtApellidoMatMod.setDisable(true);
                       txtCorreoMod.setEditable(false);
                       txtCorreoMod.setDisable(true);
                       seriesMod.setDisable(true);
                       btnModifReg.setDisable(true);


                        lblMod.setText("ALUMNO " + Nombre + " " + ApellidoPat + " " + ApellidoMat + " ACTUALIZADO CORRECTAMENTE");
                        lblMod.setTextFill(Color.web("#00E500"));
                    }
                }
            }
    }
    
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      loadData();//se hace carga de choicebox
        
    }   
    
    
    
}
