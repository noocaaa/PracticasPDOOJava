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
public class CasillaSorpresa extends Casilla{
    
    private MazoSorpresas mazos = null;
        
    CasillaSorpresa(MazoSorpresas mazo, String nombre) { // DONE!!
        // tipo sorpresa
        super(nombre);
        this.mazos = mazo;
    }
      
    @Override
    void recibeJugador(int iactual,  ArrayList<Jugador> todos){ // DONE!!
        if (super.jugadorCorrecto(iactual, todos)) {
            Sorpresa sorpresa = mazos.Siguiente();
            super.informe(iactual, todos);
            sorpresa.aplicarAJugador(iactual, todos);
        }
    }
    
    @Override
    public String toString() {
         return  "\n Nombre: " + super.getNombre() + "\n Tipo Casilla: SORPRESA";
    }
}
