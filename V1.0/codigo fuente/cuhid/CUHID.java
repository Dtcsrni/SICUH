//Este programa ha sido creado por Erick Renato Vega Cerón para el grupo 17 de Ingeniería en Sistemas Computacionales
//Centro Universitario Hidalguense
//erick@armony.systems

package cuhid;
import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;
import javax.swing.*;

import java.nio.ByteBuffer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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
     void EscribirFichero() throws IOException{

        String ruta = "datos/archivo.txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            System.out.println("Archivo inicializado correctamente");
        } else {
            System.err.println("No se ha podido iniciar el archivo");
        }
        bw.close();
    }
    static String[] buscarClave(int clave) throws IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String[] parts ={};
        try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
                        
			archivo = new File ("src/archivo/personas.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
                        
 			// Lectura del fichero
                        String texto = "";
                        while (texto != null){
                            texto=br.readLine(); 

                            if(texto!=null){     
                            parts = texto.split(",");
                            if(Integer.toString(clave).equals(parts[0])) 
                            break;
                            }   
                        }
                        br.close();
                        fr.close();
                            
                        
                        
			
        }
        catch(Exception e){
            
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{
              if( null != fr ){
                 fr.close();
              }
           }catch (Exception e2){
              e2.printStackTrace();
           }
        }
        
        if(parts[0].contains(Integer.toString(clave))){
                           
                             return parts;}
                        else
                            return null;
    }
    

    
   


    
    public static void main(String[] args) throws IOException, InterruptedException{
         /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });*/
       
        
// create a jframe
    //Declaracion de Strings
    String msg = "<html><center>Bienvenido</center><br> Elija una Acción";
    String msjTarjeta="Esperando Tarjeta";
    String[] options = {"Monitoreo de tarjetas", "Registro de Tarjetas", "Registro de Asistencias", "Acceso invitados", "Salir"};
    String[] options2 = {"Regresar"};
    
    
    //Creacion de objeto de puerto
    SerialPort sp = SerialPort.getCommPort("COM3"); // device name TODO: must be changed
    sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
    sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

       //Creacion de la etiqueta
    final JLabel label = new JLabel(msjTarjeta, JLabel.CENTER);

    //Variables del marco contenedor de la interfáz de usuario
    JFrame marco = new JFrame("CUHID");
    marco.setVisible(true);
    marco.setSize(0, 0);
    marco.setLocation(500, 500);
    marco.setTitle("CUHID"); 
    marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    //Variables de fichero e inicialización de ficheros
  
    
    int salida=0;
    //Creación de iconos para usar en interfaz
    ImageIcon iconCUH = createImageIcon("/image/cuhpng_1.png");//Creamos el ícono de CUH
    ImageIcon iconRFID = createImageIcon("/image/RFID.png");//Creamos el ícono de RFID
  do{  
    // Mostrar una ventana de dialogo pidiendo opciones
    int opcion=JOptionPane.showOptionDialog(marco, "<html><br>Sistema de Identificacion del Centro Universitario Hidalguense",
                "CUHID Alpha",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, iconCUH, options, options[0]);
   Thread monitoreo = new Thread(new Runnable(){
        public void run(){
         int n = JOptionPane.showOptionDialog(marco,
"Monitoreo de Tarjetas Activo...",
"CUHID-MONITOREO Activo",
JOptionPane.OK_OPTION,
JOptionPane.QUESTION_MESSAGE,
iconRFID,     //do not use a custom Icon
options2,  //the titles of buttons
options[0]); 
         
        }
    }); 
    
    switch(opcion){
        case 0://Monitoreo
            abrirPuerto(sp);
            monitoreo.start();
            
            System.out.println("Monitoreo Activo");
            do{//Revisar estado de tarjeta
        rEstadoTarjeta(sp);
        Thread.sleep(900);    
                }while(monitoreo.isAlive());
            cerrarPuerto(sp);
            break;
        case 1:
            
        break;
        case 2:
        break;
        case 3:
        break;    
        case 4:
            salida=1;
            System.exit(0);
           break;
        
    }
    }while(salida!=1);
        
        
    
    }
    static int decodificar(byte[] codigo){
        int converso;
        ByteBuffer wrapped = ByteBuffer.wrap(codigo); // big-endian by default
        converso = wrapped.getInt(); // 1
        System.err.println(converso);
        return converso;
    }
    static void rEstadoTarjeta(SerialPort sp) throws IOException, InterruptedException{
        
        
        
        String mensajeDatos="";
        int encontrado=0;
        int cEnviada=0;
        String mensajeI="CUH";
        boolean tarjeta=false;
        byte[] clave = new byte[11];
        if(sp.getInputStream().available()>0){//Si hay instrucciones en el serial         
        encontrado=0;    
        sp.getInputStream().read(clave);
        cEnviada=decodificar(clave);
        tarjeta=true;
                                           }
        if (tarjeta){
            tarjeta=false;
            String[] persona;
            if((persona=buscarClave(cEnviada))!=null){
                String msjNombre=persona[1];
                String mensajeS="CUHS"+";"+msjNombre;
                String mensajeN="CUHN";
             sp.getOutputStream().write(mensajeS.getBytes());
             sp.getOutputStream().flush();             
             System.out.println("Acceso Concedido");
             System.out.println("Bienvenido "+msjNombre);
             Thread.sleep(4000);
             encontrado=1;
            }
            if(encontrado==0){
                System.out.println("La persona es "+persona);
             String mensajeN="CUHN";
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

