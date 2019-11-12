/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author evega
 */
public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try{
        FXMLLoader loader = new FXMLLoader();    
        AnchorPane root = loader.load(getClass().getResourceAsStream("/vista/login.fxml"));
        
        
        
        Scene scene = new Scene(root);    
        primaryStage.setTitle("Sistema de Identificación del Centro Universitario Hidalguense");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        }
        catch(Exception ex){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE,null,ex);
        }
       
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
