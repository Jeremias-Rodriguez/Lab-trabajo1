/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 *
 * @author jerexio
 */
public class Doblar implements EstrategiaCoduccion {

    @Override
    public void conducir(Colectivo colectivo) {
        Ubicacion 
                ubic = colectivo.getUbicacion(),
                nuevaUbic = Ubicacion.obtenerSiguenteRuta(ubic);
        
        if(ubic != null)
            ubic.actualizarUbicacion(nuevaUbic.getNombreRuta(), 0);
        else
            System.out.println("No puede cambiar de ruta");
    }
    
}
