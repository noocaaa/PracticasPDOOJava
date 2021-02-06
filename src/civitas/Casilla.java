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
public class Casilla {

    private String nombre;
    
    Casilla(String nombre) { // DONE!!
        // tipo descanso
        this.nombre = nombre;
    }


    public String getNombre(){ // DONE!!
        return nombre;
    }

    void informe(int iactual, ArrayList<Jugador> todos) { // DONE!!
        String texto = "El jugador " + todos.get(iactual).getNombre() + " se ha movido a la casilla " + nombre;
        Diario.getInstance().ocurreEvento(texto);
        toString();
    }    
    
    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos){ // DONE!!
        boolean estado;
        if (iactual < todos.size() && iactual >=0) {
            estado = true;
        } else {
            estado = false;
        }
        return estado;
    }
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos){ // DONE!!
            informe(iactual, todos);
    }
    
    @Override
    public String toString(){ // DONE!!
        return  "\n Nombre: " + this.nombre + "\n Tipo Casilla: DESCANSO";
    }
}
