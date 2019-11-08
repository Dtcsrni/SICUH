/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;



public class InterfazInicioController implements Initializable {
    ObservableList list=FXCollections.observableArrayList();
    @FXML private ChoiceBox<String> series;
    @FXML private Label ChoiceBoxLabel;
    
    
    
    private void loadData(){
        list.removeAll(list);
        String a = "Ingeniería en Sistemas";
        String b = "Psicología";
        String c = "Derecho";
        String d = "Educación";
        list.addAll(a,b,c,d);
        series.getItems().addAll(list);
        
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      loadData();
        
    }   
    
    
    
}
