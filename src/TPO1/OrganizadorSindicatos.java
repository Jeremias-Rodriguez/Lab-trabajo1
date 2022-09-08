/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

import Interfaz.Interfaz;
import java.util.Random;
import java.util.concurrent.Callable;
import javax.swing.JLabel;

/**
 * Soy un organizador de sindicatos
 * aqui crean sus piquetes, sin pisarse
 * Para que dos sindicatos no esten en el mismo lugar
 * @author jerexio
 */
public class OrganizadorSindicatos {
    
    private Piquteros piqueteros;
    private Lista lugaresParaPiquete;
    private Mapa mapa;
    private Interfaz interfaz;
    
    public OrganizadorSindicatos(Piquteros piqueteros, Mapa mapa, Interfaz interfaz){
        this.piqueteros = piqueteros;
        this.mapa = mapa;
        this.interfaz = interfaz;
        
        lugaresParaPiquete = new Lista();
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 1",1,17), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 3",3,6), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 3",3,16), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 3",3,19), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 3",3,22), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 5",5,9), 1);
    }
    
    public boolean mandarPiquete(Piquete piquete){
        return piqueteros.realizarPiquete(crearTareaPiquete(piquete));
    }
    
    public synchronized void finalizarPiquete(Ubicacion ubicacion){
        lugaresParaPiquete.insertar(ubicacion, 1);
    }
    
    private Callable crearTareaPiquete(Piquete nuevoPiquete){
        Callable piquete = null;
        if(nuevoPiquete != null){
            piquete = new Callable() {
                @Override
                public Object call() {
                    boolean exito = false;
                    Ubicacion lugar = nuevoPiquete.getUbicacion();
                    //Informa al noticiero
                    int posX = lugar.getPosX(),
                        posY = lugar.getPosY();
                    
                    if(mapa.establecerPiquete(posX, posY)){
                        interfaz.informarANoticiero("INICIO de un piquete en la "+lugar.getNombreRuta()+
                            " km "+lugar.getPosX()*lugar.getPosY());
                        
                        JLabel labelDelete = interfaz.aparecerPiqueteros(posY*50, posX*100);
                        
                        try {
                            Thread.sleep(nuevoPiquete.getDuracionMillis());
                        } catch (InterruptedException ex) {
                            System.out.println("Error Organizador de sindicatos - Linea 56");
                            ex.printStackTrace();
                        }
                        
                        mapa.destablecerPiquete(posX, posY);
                        interfaz.informarANoticiero("FINALIZO el piquete en la "+lugar.getNombreRuta()+
                            " km "+lugar.getPosX()*lugar.getPosY());
                        
                        interfaz.sacarImagen(labelDelete);
                        
                        exito = new Random().nextBoolean();
                    }
                    
                    return exito;
                }
            };
        }
        return piquete;
    }
        
    
    public synchronized Piquete crearPiquete(){
        Random random = new Random();
        Piquete nuevoPiquete = null;
        
        if(!lugaresParaPiquete.esVacia()){
            int num = (random.nextInt(lugaresParaPiquete.longitud()))+1;
            
            Ubicacion ubic = (Ubicacion)lugaresParaPiquete.recuperar(num);
            
            lugaresParaPiquete.eliminar(num);

            long duracion = (random.nextInt(10)+1) * 1000;
            nuevoPiquete = new Piquete(ubic, duracion);
        }
        
        return nuevoPiquete;
    }
}
