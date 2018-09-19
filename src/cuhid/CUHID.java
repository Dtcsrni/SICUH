/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuhid;
import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;
import javax.swing.*;
import java.awt.*;
import java.nio.ByteBuffer;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

/**
 *
 * @author Erick Vega
 */
public class CUHID extends JPanel{
    
    //Código experimental de funcionamiento con pestañas (pronto a implementarse)
    /*public CUHID(){
       super(new GridLayout(1, 1)); 
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/cuhpng_cJP_icon.ico");
         
        JComponent panel1 = makeTextPanel("Identificacion de tarjetas");
        tabbedPane.addTab("Monitoreo ID", icon, panel1,
                "Monitoreo activo de tarjetas ID");

         
        JComponent panel2 = makeTextPanel("Añadir tarjetas");
        tabbedPane.addTab("Registro", icon, panel2,
                "Registro de tarjetas y asociación con alumno/personal");

         
        JComponent panel3 = makeTextPanel("Acceso Invitado");
        tabbedPane.addTab("Acceso invitado", icon, panel3,
                "Still does nothing");

         
        JComponent panel4 = makeTextPanel(
                "Registro de asistencias");
        panel4.setPreferredSize(new Dimension(800, 600));
        tabbedPane.addTab("Asistencias", icon, panel4,
                "Registro de asistencias");

         
        //Add the tabbed pane to this panel.
        add(tabbedPane);
         
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CUHID.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }/*
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Add content to the window.
        frame.add(new CUHID(), BorderLayout.CENTER);
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    */
    public static void main(String[] args) throws IOException, InterruptedException{
         /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });*/
       
        
// create a jframe
    JFrame marco = new JFrame("CUHID");
    String msg = "<html><center>Bienvenido</center><br> Elija una Acción";
    String msjTarjeta="Esperando Tarjeta";
    //Creacion de objeto para puerto
    SerialPort sp = SerialPort.getCommPort("COM3"); // device name TODO: must be changed
    sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
    sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written
    //Fin de creacion de objeto para puerto
    int erick=892547376;
    boolean salir=false;
    final JLabel label = new JLabel(msjTarjeta, JLabel.CENTER);
    int ventana=0;
    marco.setVisible(true);
     
    String[] options = {"Monitoreo de tarjetas", "Registro de Tarjetas", "Registro de Asistencias", "Acceso invitados"};
    String[] options2 = {"Regresar"};
    ImageIcon iconCUH = createImageIcon("/image/cuhpng_1.png");//Creamos el ícono de CUH
    // show a joptionpane dialog using showMessageDialog
    int opcion=JOptionPane.showOptionDialog(null, "<html><br>Sistema de Identificacion del Centro Universitario Hidalguense",
                "CUHID Alpha",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, iconCUH, options, options[0]);
   Thread monitoreo = new Thread(new Runnable(){
        public void run(){
            int n = JOptionPane.showOptionDialog(marco,
"Monitoreando Tarjeta",
"CUHID-MONITOREO",
JOptionPane.OK_OPTION,
JOptionPane.QUESTION_MESSAGE,
null,     //do not use a custom Icon
options2,  //the titles of buttons
options[0]); 
        }
    }); 
    
    switch(opcion){
        case 0:
            abrirPuerto(sp);
            monitoreo.start();
            System.out.println("Monitoreo Activo");
            do{//Menu
        rEstadoTarjeta(sp);
        Thread.sleep(900);     
                }while(opcion!=JOptionPane.OK_OPTION);
            cerrarPuerto(sp);
            break;
        case 1:
        break;
        case 2:
        break;
        case 3:
        break;    
        
        
    }
    
        
        
    
    }
    static int decodificar(byte[] codigo){
        int converso;
        ByteBuffer wrapped = ByteBuffer.wrap(codigo); // big-endian by default
        converso = wrapped.getInt(); // 1
        System.err.println(converso);
        return converso;
    }
    static void rEstadoTarjeta(SerialPort sp) throws IOException, InterruptedException{
        String nombre="Erick Vega";
        String mensajeS="CUHS"+";"+nombre;
        String mensajeN="CUHN";
        
        String mensajeDatos="";
        int encontrado=0;
        int cEnviada=0;
        boolean tarjeta=false;
        byte[] clave = new byte[11];
        if(sp.getInputStream().available()>0){
        encontrado=0;    
        sp.getInputStream().read(clave);
        cEnviada=decodificar(clave);       
        tarjeta=true;
                                           }
        if (tarjeta){
            tarjeta=false;
            int erick=892547376;
            if(cEnviada==erick){
             sp.getOutputStream().write(mensajeS.getBytes());
             sp.getOutputStream().flush();
             String msjNombre="Erick Vega";
             System.out.println("Acceso Concedido");
             System.out.println("Bienvenido "+msjNombre);
             Thread.sleep(4000);
             encontrado=1;
            }
            if(encontrado==0){
             sp.getOutputStream().write(mensajeN.getBytes());
             sp.getOutputStream().flush();
             System.out.println("Acceso Denegado");
            Thread.sleep(4000); 
            }           
        }
    }
    static void abrirPuerto(SerialPort sp)throws IOException, InterruptedException{
        System.out.println("Abriendo puerto...");
    if (sp.openPort()) {
      System.out.println("Puerto abierto con exito");
    } else {
      System.out.println("El puerto no ha podido abrirse");
      return;
    }          
     Thread.sleep(900);
    }
    static void cerrarPuerto(SerialPort sp)throws IOException, InterruptedException{
        if (sp.closePort()) {
      System.out.println("El puerto ha sido cerrado con exito");
    } else {
      System.out.println("No se ha podido cerrar el puerto");   
    }
    }
}

