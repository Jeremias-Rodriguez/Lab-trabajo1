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
 * @author repetto.francisco
 */
public class OrganizadorSindicatos {
    
    private GrupoDePiqueteros piqueteros;
    private Lista lugaresParaPiquete;
    private Mapa mapa;
    private Interfaz interfaz;
    
    public OrganizadorSindicatos(GrupoDePiqueteros piqueteros, Mapa mapa, Interfaz interfaz){
        this.piqueteros = piqueteros;
        this.mapa = mapa;
        this.interfaz = interfaz;
        
        lugaresParaPiquete = new Lista();
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 1",3,6), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 3",3,8), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 3",1,7), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 3",3,10), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 3",5,5), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta 5",3,4), 1);
    }
    
    /**
     * Recibe un piquete
     * @param piquete
     * Crea una tarea con las especificaciones de ese piquete y lo envia a
     * los piqueteros para que lo realicen
     * @return retorna si fue exitoso el piquete
     */
    public boolean mandarPiquete(Piquete piquete){
        return piqueteros.realizarPiquete(crearTareaPiquete(piquete));
    }
    
    /**
     * Cuando finaliza un piquete se debe volver a colocar la ubicacion
     * para que este disponible para que otro sindicato la ocupe
     * @param ubicacion 
     */
    public synchronized void finalizarPiquete(Ubicacion ubicacion){
        lugaresParaPiquete.insertar(ubicacion, 1);
        this.notify();
    }
    
    /**y 
    * Recibe un piquete
     * @param nuevoPiquete
     * Crea una tarea, o sea un Callable con las especificaciones de ese piquete
     * @return la tarea para ese piquete
     */
    private Callable crearTareaPiquete(Piquete nuevoPiquete){
        Callable piquete = null;
        if(nuevoPiquete != null){
            piquete = new Callable() {
                
                /**
                 * Primero verifica si puede establecer el piquete en
                 * la ubicacion correspondiente
                 * En caso de poder, informa al noticiero, ubica la imagen de
                 * los piqueteros, simula el tiempo
                 * Luego quita el piquete, informa al noticiero y elimina la
                 * imagen de la parte grafica
                 * 
                 * Por ultimo, como el callable retorna algo, hicimos que
                 * informe si el piquete fue exitoso
                 * .
                 */
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
                        
                        JLabel labelDelete = interfaz.aparecerPiqueteros(posY*100-50, posX*100-50);
                        
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
        
    /**
     * Se encarga de crear un piquetes.
     * Verifica si hay ubicaciones para hacer piquetes,
     * en caso de haber, ocupa 1. Para ocupar debe quitar la ubicacion de 
     * los lugaresParaPiquete. La duracion del piquete es random, y la seleccion
     * del lugar tambien
     * @return 
     */
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
        else{
            try {
                this.wait();
            } catch (InterruptedException ex) {
            }
        }
        
        return nuevoPiquete;
    }
}
