/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

import java.util.Scanner;

/**
 * Punto de interacion entre colectivo y piqueteros
 * 
 * @author jerexio
 */
public class Ruta {
    private Mapa mapa;
    private Colectivo colectivo;
    private Ubicacion posActualColectivo;
    
    private Scanner input = new Scanner(System.in);
    
    public Ruta(Mapa mapa, Colectivo colectivo) {
        this.mapa = mapa;
        this.colectivo = colectivo;
        this.posActualColectivo = colectivo.getUbicacion();
    }
    
    public void inicio(){
        System.out.println("Estas en el punto A");
        System.out.println("El objetivo es simple llegar a B en el menor tiempo posible");
        System.out.println("Durante tu aventura apareceran piqueteros con protestas"
                + " justas");
        System.out.println("Buena suerte");
        
        System.out.print("Usted se encuentra en: ");
        System.out.println(posActualColectivo.getNombreRuta());
    }
    
    public void ejecutar(){
        boolean llegoADestino = false;
        int opcion = 0;
        llegoADestino = mapa.esDestino(posActualColectivo.getPosX(), posActualColectivo.getPosY()+1);
    }
    
    public void seguir(){
        colectivo.ejecutarEstrategia(new Seguir());
    }
    
    public void esperar(){
        colectivo.ejecutarEstrategia(new Esperar());
    }
    
    public void doblar(){
        colectivo.ejecutarEstrategia(new Doblar(mapa));
    }
}
