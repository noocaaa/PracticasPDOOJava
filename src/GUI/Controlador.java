/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.CivitasJuego;
import civitas.GestionesInmobiliarias;
import civitas.OperacionInmobiliaria;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;

/**
 * @author noelia
 */
public class Controlador {
    
    private CivitasJuego juego;
    private CivitasView vista;
    
    Controlador(CivitasJuego juego, CivitasView vista) { //DONE!
        this.juego = juego;
        this.vista = vista;
    }
    
    void juega() {
        vista.setCivitasJuego(juego);
        while (!juego.finalDelJuego()) {
            vista.actualizarVista();
            //vista.pausa();
            OperacionesJuego next = juego.siguientePaso();
            if (next != OperacionesJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
            
           if (!juego.finalDelJuego()) {
               switch (next) {
                    case COMPRAR:
                        
                        Respuestas respuesta = vista.comprar();
                        
                        if (respuesta == Respuestas.SI) {
                           juego.comprar();
                        } 
                        
                        juego.siguientePasoCompletado(next);  
                        
                       break;
                    case GESTIONAR:
                        vista.gestionar();
                        OperacionInmobiliaria operacionin = new OperacionInmobiliaria(GestionesInmobiliarias.values()[vista.getGestion()], vista.getPropiedad());
                        
                        switch (operacionin.getGestion()) {
                            case TERMINAR:
                                juego.siguientePasoCompletado(next);
                                break;
                            case CANCELAR_HIPOTECA:
                                juego.cancelarHipoteca(operacionin.getNumPropiedad());
                                break;
                            case CONSTRUIR_CASA:
                                juego.construirCasa(operacionin.getNumPropiedad());
                                break;
                            case CONSTRUIR_HOTEL:
                                juego.construirHotel(operacionin.getNumPropiedad());
                                break;
                            case HIPOTECAR:
                                juego.hipotecar(operacionin.getNumPropiedad());
                                break;
                            case VENDER:
                                juego.vender(operacionin.getNumPropiedad());
                                break;                        
                        }
                        
                        break;
                    case SALIR_CARCEL:
                        
                        if (vista.salirCarcel() == SalidasCarcel.PAGANDO) {
                            juego.salirCarcelPagando();                            
                        } else if (vista.salirCarcel() == SalidasCarcel.TIRANDO) {
                            juego.salirCarcelTirando();
                        }
                        
                        juego.siguientePasoCompletado(next);
                        
                        break; 
               }
           } else {
               juego.ranking();
           }
        }
    }
}
