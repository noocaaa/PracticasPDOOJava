/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 * DEBE REPRESENTAR EL TABLERO DE JUEGO IMPONIENDO LAS RESTRICCIONES EXISTENTES SOBRE EL MISMO EN LAS REGLAS DEL JUEGO.
 * @author noelia
 */
public class Tablero {
    
    //atributos de instancia privados
    private ArrayList<Casilla> casillas; //es el contenedor de las casillas del juego
    private int numCasillaCarcel; //num de casilla donde esta la carcel
    private int porSalida; //num de veces que se ha pasado por la salida en un turno
    private boolean tieneJuez; //representar si el tablero dispone de la casilla de ese tipo
    
    public Tablero(int numCasillaCarcel) { //DONE!
        Casilla Salida = new Casilla("Salida");
        
        if (numCasillaCarcel >= 1){
            this.numCasillaCarcel = numCasillaCarcel;
        } else {
            this.numCasillaCarcel = 1;
        }
        porSalida = 0;
        tieneJuez = false;
        casillas = new ArrayList<>(); //la inicializamos vacio
        casillas.add(Salida); //añadimos la casilla salida
       
    }
    
    private boolean correcto() { //CON ESTO NOS ASEGURAMOS QUE TENEMOS UNA CASILLA TIPOJUEZ Y SE HA AÑADIDO LA CARCEL A LA POSICIÓN INDICADA EN EL CONSTRUCTOR
        for (int i=0; i < casillas.size(); i++) {
            if (casillas.get(i).getNombre() == "Juez") {
                tieneJuez = true;
            }
        }
        
        if (casillas.size() > numCasillaCarcel && tieneJuez) {
            return true; //TABLERO APTO PARA JUGAR
        } else {
            return false;
        }
    }
        
    private boolean correcto(int numCasilla) {
        if (correcto() && numCasilla >= 0 && numCasilla < casillas.size()) {
            return true;
        } else {
            return false;
        }
    }
    
    int getCarcel() { //DONE!
        return numCasillaCarcel;
    }
    
    int getPorSalida() { //DONE!
        if (porSalida > 0) {
            porSalida--;
            return porSalida + 1;
        } else {
            return porSalida;
        }
    }
    
    void añadeCasilla(Casilla casilla) {          
        if (casillas.size() == numCasillaCarcel) {
            Casilla Carcel = new Casilla("Carcel");
            casillas.add(Carcel);
        }
        
        casillas.add(casilla);
        
        if (casillas.size() == numCasillaCarcel) {
            Casilla Carcel = new Casilla("Carcel");
            casillas.add(Carcel);
        }   
    }
    
    void añadeJuez() { //DONE!
        for (int i=0; i < casillas.size(); i++) { //nos aseguramos de que no este, para no crearlo duplicado
            if (casillas.get(i).getNombre() == "Juez") {
                tieneJuez = true;
            }
        }
        if (!tieneJuez) {
            Casilla Juez = new CasillaJuez(getCarcel(), "Juez");
            casillas.add(Juez);
            tieneJuez = true;
        }
    }
    
    Casilla getCasilla(int numCasilla) { //DONE!
        if (correcto(numCasilla)) {
            return casillas.get(numCasilla);
        } else {
            return null;
        }
    }
    
    int nuevaPosicion(int actual, int tirada) { //DONE!
        int salida;
        
        if (correcto()){ //tablero correcto
            int npartida = actual + tirada;
            
            if (npartida > casillas.size()) {
                porSalida++;
                salida = npartida - casillas.size();
            } else {
                salida = npartida;
            }
            
        } else {
            salida=-1;
        }
        
        return salida;
    }
    
    int calcularTirada (int origen, int destino) { //DONE!
        
        int salida, cal = destino - origen;
        
        if (cal < 0) {
            salida = cal + casillas.size();
        } else {
            salida = cal;
        }
        
        return salida + 1;
    }
    
    ArrayList<Casilla> getCasillas(){ //DONE!
        return casillas;
    }
}
