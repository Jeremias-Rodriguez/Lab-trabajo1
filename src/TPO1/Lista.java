/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO1;

/**
 *
 * @author jerem
 */
public class Lista {
    //EL toString ESTA COMO COMENTARIO
    
    private Nodo cabecera;
    private int longitud;
    
    public Lista(){
        this.cabecera = null;
        this.longitud = 0;
    }
    
    public boolean insertar(Object nuevoElem, int pos){
        boolean exito = true;
        
        if(pos < 1 || pos > this.longitud+1){ //Si es menor a 1 o mayor a la pos maxima, no se puede insertar
            exito = false;
        }
        else{
            if(pos == 1){ //Insertar primer elemento
                this.cabecera = new Nodo(nuevoElem,this.cabecera);
            }
            else{ //Insertar elemento que no es el primero
                Nodo aux = this.cabecera;
                int i = 1;
                
                while(i < pos-1){ //while para recorrer hasta llegar a la posicion donde se insertara
                    aux = aux.getEnlace();
                    i++;
                }
                
                Nodo nuevo = new Nodo(nuevoElem,aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            this.longitud++;
        }
        return exito;
    }
    
    public boolean eliminar(int pos){
        boolean exito = true;
        
        if(pos < 1 || pos > this.longitud){
            exito = false;
        }
        else{
            if(pos == 1){
                this.cabecera = this.cabecera.getEnlace();
            }
            else{
                Nodo aux = this.cabecera;
                int i = 1;
                while(i < pos-1){
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            this.longitud--;
        }
        return exito;
    }
    
    public Object recuperar(int pos){
        Object elem;
        
        if(pos < 1 || pos > this.longitud){
            elem = null;
        }
        else{
            Nodo aux = this.cabecera;
            int i = 1;
            while(i < pos){
                aux = aux.getEnlace();
                i++;
            }
            elem = aux.getElem();
        }
        return elem;
    }
    
    public int localizar(Object elem){
        int pos = 1;
        Nodo aux = this.cabecera;
        while(pos <= this.longitud && !aux.getElem().equals(elem)){
            aux = aux.getEnlace();
            pos++;
        }
        if(pos > this.longitud){
            pos = -1;
        }
        return pos;
    }
    
    public int longitud(){
        return this.longitud;
    }
    
    public boolean esVacia(){
        boolean vacia;
        
        vacia = (this.cabecera == null);
        
        return vacia;
    }
    
    public void vaciar(){
        this.cabecera = null;
        this.longitud = 0;
    }
    
    public Lista clone(){
        Lista clonLista = new Lista();
        Nodo aux = this.cabecera, aux2;
        
        if(this.cabecera != null){
                clonLista.cabecera = new Nodo(aux.getElem(),null);
                aux = aux.getEnlace();
                aux2 = clonLista.cabecera;
        
            while(aux != null){
                aux2.setEnlace(new Nodo(aux.getElem(),null));
                aux2 = aux2.getEnlace();
                aux = aux.getEnlace();
            }
            clonLista.longitud = this.longitud;
        }
        
        return clonLista;
    }
    
    public Lista invertir(){
        //Hace un clon con la lista invertida
        Lista listaInvert = new Lista();
        
        if(this.cabecera != null){
            Nodo aux = this.cabecera;
        
            listaInvert.cabecera = new Nodo(aux.getElem(), null);
            aux = aux.getEnlace();
        
            while(aux != null){
                listaInvert.cabecera = new Nodo(aux.getElem(), listaInvert.cabecera);
                aux = aux.getEnlace();
            }
            
            listaInvert.longitud = this.longitud;
        }
        
        return listaInvert;
    }
    
    public boolean eliminarAparicion(Object elem){
        boolean seElimino = false;
        //Elimina un elemento de la lista
        Nodo aux = this.cabecera.getEnlace(), aux2 = this.cabecera;
        
        if(this.cabecera.getElem().equals(elem)){
                this.cabecera = this.cabecera.getEnlace();
                seElimino = true;
        }
        
        while(aux != null && !seElimino){
           
            if(aux.getElem().equals(elem)){
                aux2.setEnlace(aux.getEnlace());
                seElimino = true;
            }
            aux = aux.getEnlace();
            aux2 = aux2.getEnlace();
        }
        
        return seElimino;
    }
    
    public String toString(){
        String cadena = "";
        
        if(this.cabecera == null){
            cadena = "Lista Vacia";
        }
        else{
            Nodo aux = this.cabecera;
            cadena = "[ " + aux.getElem().toString();
            aux = aux.getEnlace();
            
            while(aux != null){
                cadena += " - " + aux.getElem().toString();
                aux = aux.getEnlace();
            }
            
            cadena += " ]";
            cadena += "\nCabecera: "+this.cabecera.getElem();
        }
        return cadena;
    }
}
