/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

import java.util.Scanner;
import javax.xml.transform.OutputKeys;

/**
 * Punto de interacion entre colectivo y piqueteros
 * 
 * @author jerexio
 */
public class Ruta {
    private Ubicacion[] puntosPiquete;
    private Colectivo colectivo;

    private Scanner input = new Scanner(System.in);
    
    public Ruta(Ubicacion[] cambiosDeCalle, Ubicacion[] puntosPiquete, Colectivo colectivo) {
        //this.cambiosDeCalle = cambiosDeCalle;
        this.puntosPiquete = puntosPiquete;
        this.colectivo = colectivo;
    }
    
    public void inicio(){
        System.out.println("Estas en el punto A");
        System.out.println("El objetivo es simple llegar a B en el menor tiempo posible");
        System.out.println("Durante tu aventura apareceran piqueteros con protestas"
                + " justas");
        System.out.println("Buena suerte");
    }
    
    public void empezar(){
        boolean llegoADestino = false;
        int opcion = 0;
        
        colectivo.ejecutarEstrategia(new Seguir());
        
        while(!llegoADestino){
            System.out.println("Usted se encuentra en: ");
            Ubicacion ubicacion = colectivo.getUbicacion();
            System.out.println(ubicacion.getNombreRuta()+" - "+ubicacion.getKm());
            
            System.out.println("Que desea hacer?");
            opcion = input.nextInt();
            switch(opcion){
                case 1:
                    colectivo.ejecutarEstrategia(new Seguir());
                    
                    break;
                case 2:
                    colectivo.ejecutarEstrategia(new Esperar());
                    break;
                case 3:
                    colectivo.ejecutarEstrategia(new Doblar());
                    break;
                default:
                    System.out.println("No");
                    break;
            }
        }
        
    }
}
