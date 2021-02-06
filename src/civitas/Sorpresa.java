/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 * @author noelia
 */
abstract class Sorpresa {
    protected String texto;
    protected int valor;
    private MazoSorpresas mazo;
    private Tablero tablero;
   
    Sorpresa(int valor, String texto) { // RESTO DE SORPRESAS
        this.valor = valor;
        this.texto = texto;
        this.mazo = null;
        this.tablero = null;
    } 
    
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);
    
    protected void informe(int actual, ArrayList<Jugador> todos){ // DONE!
        String evento = "Se esta aplicando una sorpresa al Jugador " + todos.get(actual).getNombre();
        Diario.getInstance().ocurreEvento(evento);
    }
    
    boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){ // DONE!
        boolean estado;
        
        if (actual >=0 && actual < todos.size()) {
            estado = true;
        } else {
            estado = false;
        }
        
        return estado;
    }
    
    @Override
    public String toString(){ // DONE!
        return "El nombre de la sorpresa es: " + texto + "\n Valor: " + this.valor;
    }

}
