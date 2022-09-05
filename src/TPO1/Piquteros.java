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
 *
 * @author repetto.francisco
 */
public class Piquteros {
    private int numPiqueterosTotal = 5;
    private ScheduledThreadPoolExecutor piqueteros = new ScheduledThreadPoolExecutor(numPiqueterosTotal);

    public boolean realizarPiquete(Callable piquete) {
        System.out.println("\nDENTROOO\n");
        
        Future<Boolean> exito = piqueteros.submit(piquete);
        
        boolean ubic = false;
        try {
            ubic = exito.get();
        } catch (InterruptedException ex) {
            System.out.println("ERrOR de interrupcion");
        } catch (ExecutionException ex) {
            System.out.println("ERROR de ejecucion");
        }
        
        return ubic;
    }
}
