/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO1;

/**
 *
 * @author repetto.francisco
 */
public class Main {
    public static void main(String[] args) {
        Piquteros piqueteros = new Piquteros();
        
        OrganizadorSindicatos org = new OrganizadorSindicatos(piqueteros);
        
        Sindicato sin = new Sindicato(org);
        sin.ejecutar();
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
        }
        System.out.println("HORA DE MATARAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        sin.fin();
    }
}
