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
public class CasillaImpuesto extends Casilla{
    private float importe;
    
    CasillaImpuesto(float cantidad, String nombre) { // DONE!!
        // tipo impuesto
        super(nombre);
        importe = cantidad;
    }
     
    @Override
    void recibeJugador(int iactual,  ArrayList<Jugador> todos){ // DONE!!
        if (super.jugadorCorrecto(iactual, todos)){
            super.informe(iactual, todos);
            todos.get(iactual).pagaImpuesto(this.importe);
        }
    }
    
    @Override
    public String toString(){ // DONE!!
        return  "\n Nombre: " + super.getNombre() + "\n Tipo Casilla: Impuesto" + "\n Importe: " + this.importe;
    }
}
