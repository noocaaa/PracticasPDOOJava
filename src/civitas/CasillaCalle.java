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
public class CasillaCalle extends Casilla{
    
    private TituloPropiedad tituloPropiedad = null;
    
    CasillaCalle(TituloPropiedad titulo) { // DONE!!
        // tipo calle
        super(titulo.getNombre());
        this.tituloPropiedad = titulo;
    }
        
    TituloPropiedad getTituloPropiedad() {
        return this.tituloPropiedad;
    }

    @Override
    void recibeJugador(int iactual,  ArrayList<Jugador> todos){ //DONE!! 
        if (super.jugadorCorrecto(iactual, todos)) {
            super.informe(iactual, todos); //1
            Jugador jugador = todos.get(iactual); //2

            if (!tituloPropiedad.tienePropietario()) {
                jugador.puedeComprarCasilla(); //3
            } else {
                tituloPropiedad.tramitarAlquiler(jugador);
            }

        }
    }
    
    @Override
    public String toString() {
        return "Nombre: " + this.getTituloPropiedad().getNombre() +  "\nTipo Casilla: CALLE";
    }
}
