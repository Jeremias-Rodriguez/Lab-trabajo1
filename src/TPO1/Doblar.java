/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 * La clase Doblar es una implementacion de la interfaz EstrategiaConduccion.
 * Su funcion es implementar la estrategia para doblar con el colectivo
 * 
 * @author jerexio
 * @author repetto.francisco
 */
public class Doblar implements EstrategiaCoduccion {

    private Mapa mapa;
    
    public Doblar(Mapa mapa){
        this.mapa = mapa;
    }
    
    @Override
    public void conducir(Colectivo colectivo) {
        Ubicacion ubic = colectivo.getUbicacion();
        
        int X = ubic.getPosX(),
            Y = ubic.getPosY();
        
        if(mapa.desplazarColectivo(X, Y, X+1, Y)){
            ubic.actualizarUbicacion(X+1, Y);
        }
        else{
            if(mapa.desplazarColectivo(X, Y, X-1, Y)){
                ubic.actualizarUbicacion(X-1, Y);
            }
            else{
                System.out.println("No se puede doblar");
            }
        }
    }
    
}
