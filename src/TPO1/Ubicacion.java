/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Esta clase tiene 2 principales funcionalidades
 * - Ser un indicador de donde esta un objeto actualmente en la ruta
 * - Indicar en que puntos del mapa se puede doblar
 * @author jerexio
 */
public class Ubicacion {
    private static AtomicInteger idCont = new AtomicInteger(0);
    
    private int idRuta;
    private String nombreRuta;
    private int posX;
    private int posY;

    public Ubicacion(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }
    
    public Ubicacion(String nombreRuta, int km) {
        this.idRuta = idCont.incrementAndGet();
        this.nombreRuta = nombreRuta;
        this.km = km
    }
    
    public void conectarRutas(Ubicacion rutaActual, Ubicacion rutaSiguiente){
        ingresoARutas.put(rutaActual, rutaSiguiente);
    }
    
    public String getNombreRuta() {
        return nombreRuta;
    }

    public int getKm() {
        return km;
    }
    
    public void actualizarUbicacion(String nuevaRuta, int km){
        this.nombreRuta = nuevaRuta;
        this.km = km;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    
    public static Ubicacion obtenerSiguenteRuta(Ubicacion ubicacionActual){
        return ingresoARutas.get(ubicacionActual);
    }

    @Override
    public String toString() {
        return "{"+nombreRuta + ", " + km + '}';
    }
}
