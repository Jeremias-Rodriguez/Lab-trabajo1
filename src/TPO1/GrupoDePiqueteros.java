/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Esta clase representa a los piqueteros que realizan las tareas de piquetes.
 * Se trata un grupo (o pool de hilos) que realizan tareas que
 * reciben por parametros
 * @author jerexio
 * @author repetto.francisco
 */
public class GrupoDePiqueteros {
    private ScheduledThreadPoolExecutor piqueteros;
    
    public GrupoDePiqueteros(int numPiqueterosTotal) {
        piqueteros = new ScheduledThreadPoolExecutor(numPiqueterosTotal);
    }
    
    /**
     * Recibe un Callable que es una tarea para realizar un piquete
     * @param piquete
     * @return booleano que indica si el piquete fue existoso
     */
    public boolean realizarPiquete(Callable piquete) {
        Future<Boolean> resultadoPiquete = piqueteros.submit(piquete);
        
        boolean exito = false;
        try {
            exito = resultadoPiquete.get();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }
        
        return exito;
    }
}
