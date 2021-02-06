/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author noelia
 */
public class CasillaJuez extends Casilla {
    
    private static int carcel;

    CasillaJuez(int numCasillaCarcel, String nombre) { // DONE!!
        // tipo juez
        super(nombre);
        this.carcel = numCasillaCarcel;
    }  
    
    @Override
    void recibeJugador(int iactual,  ArrayList<Jugador> todos){ // DONE!!
        if(super.jugadorCorrecto(iactual, todos)){
            super.informe(iactual, todos);
            todos.get(iactual).encarcelar(this.carcel);
        }
    }
    
    @Override
    public String toString() {
        return  "\n Nombre: " + super.getNombre() + "\n Tipo Casilla: JUEZ";
    }
}
