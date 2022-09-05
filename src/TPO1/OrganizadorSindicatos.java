/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Soy un organizador de sindicatos
 * aqui crean sus piquetes, sin pisarse
 * Para que dos sindicatos no esten en el mismo lugar
 * @author jerexio
 */
public class OrganizadorSindicatos {
    
    private Piquteros piqueteros;
    private Lista lugaresParaPiquete;
    
    
    /**
     * ESTO NO ES UN BUFFER CAMBIAR NOMBRE
     * @param buffer 
     */
    public OrganizadorSindicatos(Piquteros buffer){
        piqueteros = buffer;
        lugaresParaPiquete = new Lista();
        lugaresParaPiquete.insertar(new Ubicacion("Ruta1",3), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta1",10), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta2",5), 1);
        lugaresParaPiquete.insertar(new Ubicacion("Ruta3",2), 1);
    }
    
    public synchronized Piquete establecerPiqueteEnUbicacion(){
        Piquete nuevoPiquete = crearPiquete();
        
        return nuevoPiquete;
    }
    
    public boolean mandarPiquete(Piquete piquete){
        return piqueteros.realizarPiquete(crearTareaPiquete(piquete));
    }
    
    public synchronized void finalizarPiquete(Ubicacion ubicacion){
        lugaresParaPiquete.insertar(ubicacion, 1);
    }
    
    private synchronized Callable crearTareaPiquete(Piquete nuevoPiquete){
        
        Callable piquete = null;
        if(nuevoPiquete != null){
            piquete = new Callable() {
                @Override
                public Object call() {
                    System.out.println(Thread.currentThread().getName() + " haciendo piquete en "
                            + nuevoPiquete.mostrarPiquete());

                    try {
                        Thread.sleep(nuevoPiquete.getDuracionMillis());
                    } catch (InterruptedException ex) {
                        System.out.println("Al parecer la policia reprimio a los piqueteros");
                    }
                    return new Random().nextBoolean();
                }
            };
        }
        System.out.println("Ya arme la task");
        
        return piquete;
    }
    
    private synchronized Piquete crearPiquete(){
        Random random = new Random();
        Piquete nuevoPiquete = null;
        
        if(!lugaresParaPiquete.esVacia()){
            int num = (random.nextInt(lugaresParaPiquete.longitud()))+1;
            
            Ubicacion ubic = (Ubicacion)lugaresParaPiquete.recuperar(num);
            System.out.println(num);
            lugaresParaPiquete.eliminar(num);

            long duracion = random.nextInt(5) * 1000;
            nuevoPiquete = new Piquete(ubic, duracion);
        }
        
        return nuevoPiquete;
    }
}
