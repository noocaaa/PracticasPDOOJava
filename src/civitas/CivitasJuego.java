package civitas;

import GUI.Dado;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author noelia
 */
public class CivitasJuego {

        private GestorEstados gestorEstados;
        private EstadosJuego estado;
        private Tablero tablero;
        private MazoSorpresas mazo;
        private ArrayList<Jugador> jugadores;
        private int indiceJugadorActual;
  
        private void avanzaJugador(){ // DONE
            Jugador jugadorActual = jugadores.get(indiceJugadorActual);   //1.1
            int posicionActual = jugadorActual.getNumCasillaActual();  //1.2
            int tirada = Dado.getInstance().tirar();    //1.3
            int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);  //1.4
            Casilla casilla = tablero.getCasilla(posicionNueva);    //1.5
            this.contabilizarPasosPorSalida(jugadorActual); //1.6
            jugadorActual.moverACasilla(posicionNueva); //1.7
            casilla.recibeJugador(indiceJugadorActual, jugadores); //1.8
            this.contabilizarPasosPorSalida(jugadorActual); //1.9        
        }
        
        private void contabilizarPasosPorSalida(Jugador jugadorActual) { // DONE!
            while (tablero.getPorSalida() > 0) {
                jugadorActual.pasaPorSalida();
            }
        }
        
        private void inicializarMazoSorpresas(Tablero tablero) { // DONE!
            mazo.alMazo(new SorpresaIRCARCEL(tablero));
            mazo.alMazo(new SorpresaIRCARCEL(tablero));
            mazo.alMazo(new SorpresaIRCASILLA(tablero, 6, "Ve a la Casilla 6"));
            mazo.alMazo(new SorpresaIRCASILLA(tablero, 3, "Ve a la Casilla 15"));
            mazo.alMazo(new SorpresaIRCASILLA(tablero, 11, "Ve a la Casilla 11"));
            mazo.alMazo(new SorpresaPAGARCOBRAR(-1000, "Pagar a otro jugador 1000"));
            mazo.alMazo(new SorpresaPAGARCOBRAR(-1000, "Pagas a la banca"));
            mazo.alMazo(new SorpresaPAGARCOBRAR(800, "Recibes dinero"));
            mazo.alMazo(new SorpresaPORCASAHOTEL(100, "Pagas por cada casa y cada hotel"));
            mazo.alMazo(new SorpresaPORCASAHOTEL(50, "Pagas por cada casa y cada hotel"));
            mazo.alMazo(new SorpresaPORJUGADOR(100, "Recibes dinero de los demas"));
            mazo.alMazo(new SorpresaPORJUGADOR(-100, "Le das a cada jugador"));
            mazo.alMazo(new SorpresaPORJUGADOR(300, "Recibes dinero de los demas"));
            mazo.alMazo(new SorpresaSALIRCARCEL(mazo));
            mazo.alMazo(new SorpresaSALIRCARCEL(mazo));
            mazo.alMazo(new SorpresaESPECULADOR(600, "Jugador Especulador"));
        }
        
        private void inicializarTablero(MazoSorpresas mazo){ // DONE!   
            tablero = new Tablero(5);

            //tablero.añadeCasilla(new Casilla("Salida"));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Madrid", 50, 20, 100, 100, 50)));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Barcelona", 50, 20, 120, 150, 60)));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Granada", 50, 20, 100, 100, 50)));
            tablero.añadeCasilla(new CasillaSorpresa(mazo, "Primera Sorpresa"));
            
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Paris", 60, 20, 140, 200, 70)));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Lyon", 64, 20, 160, 250, 80)));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Montpellier", 80, 20, 200, 300, 90)));
            tablero.añadeCasilla(new CasillaSorpresa(mazo, "Segunda Sorpresa"));
            
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Londres", 85, 20, 220, 350, 100)));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Glasgow", 90, 20, 240, 400, 110)));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Liverpool", 95, 20, 260, 450, 120)));
            tablero.añadeCasilla(new Casilla("Parking"));
            
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Nueva York", 110, 20, 300, 500, 130)));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Chicago", 115, 20, 320, 550, 140)));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Los Angeles", 120, 20, 340, 600, 150)));
            tablero.añadeCasilla(new CasillaImpuesto(400, "impuesto"));
            tablero.añadeJuez();
            tablero.añadeCasilla(new CasillaSorpresa(mazo, "Tercera Sorpresa"));
            
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Canberra", 125, 20, 360, 650, 160)));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Melbourne", 140, 20, 400, 700, 170)));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Sydney", 145, 20, 420, 750, 180)));
            tablero.añadeCasilla(new CasillaSorpresa(mazo, "Cuarta Sorpresa"));
            
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Quebec", 155, 20, 440, 800, 190)));
            tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Ontario", 170, 20, 460, 850, 200)));

        }
        
        private void pasarTurno(){ // DONE!
            indiceJugadorActual = (indiceJugadorActual +1)%jugadores.size();
        }
        
        //visibilidad cambiada de privada a public por el metodo juega en el fichero Controlador
        public ArrayList<Jugador> ranking(){ // DONE!
            Collections.sort(jugadores);
            return jugadores;
        }
        
    
              
        public CivitasJuego(ArrayList<String> nombres) { // DONE!
            jugadores = new ArrayList<>();
            
            for (int i=0; i < nombres.size(); i++) {
                jugadores.add(new Jugador(nombres.get(i)));
            }
            
            gestorEstados = new GestorEstados();
            estado = EstadosJuego.INICIO_TURNO;
            
            indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size()-1);
            
            mazo = new MazoSorpresas();     
            
            this.inicializarTablero(mazo);
            this.inicializarMazoSorpresas(tablero);
        }
        
        public boolean cancelarHipoteca(int ip) {
            return  jugadores.get(indiceJugadorActual).cancelarHipoteca(ip);
        }
        
        public boolean comprar(){  // DONE!
            boolean res = false;
            Jugador jugadorActual = jugadores.get(indiceJugadorActual);
            int numCasillaActual = jugadorActual.getNumCasillaActual();
            CasillaCalle casilla = (CasillaCalle) tablero.getCasilla(numCasillaActual);
            TituloPropiedad titulo = casilla.getTituloPropiedad();
            res = jugadorActual.comprar(titulo);               
            return res;
        }
        
        public boolean construirCasa(int ip) { // DONE!
            return jugadores.get(indiceJugadorActual).construirCasa(ip);
        }
        
        public boolean construirHotel(int ip) { // DONE!
            return jugadores.get(indiceJugadorActual).construirHotel(ip);
        }
        
        public boolean finalDelJuego(){ // DONE!
            boolean banc = false;
            for (int i=0; i < jugadores.size() && !banc; i++){
                if (jugadores.get(i).enBancarrota())
                    banc = true;
            }
            return banc;
        }
        
        public Casilla getCasillaActual(){ // DONE!
            return tablero.getCasilla(this.getJugadorActual().getNumCasillaActual());
        }
        
        public Jugador getJugadorActual(){ // DONE!
            return jugadores.get(indiceJugadorActual);
        }
        
        public boolean hipotecar(int ip) { // DONE!
            return jugadores.get(indiceJugadorActual).hipotecar(ip);
        }
        
        public String infoJugadorTexto(){ // DONE!
            return jugadores.get(indiceJugadorActual).toString();
        }
        
        public boolean salirCarcelPagando(){ // DONE!
            return jugadores.get(indiceJugadorActual).salirCarcelPagando();
        }
        
        public boolean salirCarcelTirando(){ // DONE!
            return jugadores.get(indiceJugadorActual).salirCarcelTirando();
        }
        
        public OperacionesJuego siguientePaso(){  // DONE!
            Jugador jugadoraactual = jugadores.get(indiceJugadorActual);
            OperacionesJuego operacion = gestorEstados.operacionesPermitidas(jugadoraactual, estado);
            if (operacion == OperacionesJuego.PASAR_TURNO) {
                pasarTurno();
                siguientePasoCompletado(operacion);
            } else if (operacion == OperacionesJuego.AVANZAR) {
                avanzaJugador();
                siguientePasoCompletado(operacion);
            }
            return operacion;
        }
        
        public void siguientePasoCompletado (OperacionesJuego operacion){ // DONE!
            this.estado = gestorEstados.siguienteEstado(jugadores.get(indiceJugadorActual), estado, operacion);
        }
        
        public boolean vender(int ip) { // DONE!
            return jugadores.get(indiceJugadorActual).vender(ip);
        }
        
        
}
