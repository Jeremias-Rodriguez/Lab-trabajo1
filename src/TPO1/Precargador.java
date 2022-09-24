/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Esta clase se encarga de precargar el mapa matriz, desde un archivo de texto
 * al mapa.
 * @author jerexio
 * @author repetto.francisco
 */
public class Precargador {
    public static void precargar(int[][] matriz){
        try {
            //ruta de tu archivo
            String ruta = "./src/Extra/precarga.txt";
            BufferedReader br = getBuffered(ruta);
            //leemos la primera linea
            String linea =  br.readLine();
            
            //contador
            int contador = 0;
            while(linea != null){
                String[] values = linea.split(",");
                //recorremos el arrar de string
                for (int i = 0; i<values.length; i++) {
                    //se obtiene el primer caracter de el arreglo de strings
                    matriz[contador][i] = Integer.valueOf(values[i]);
                }
                contador++;
                linea = br.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    public static BufferedReader getBuffered(String link){
        FileReader lector  = null;
        BufferedReader br = null;
        try {
             File Arch=new File(link);
            if(!Arch.exists()){
                System.out.println("No existe el archivo");
            }else{
                lector = new FileReader(link);
                br = new BufferedReader(lector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return br;
    }
}
