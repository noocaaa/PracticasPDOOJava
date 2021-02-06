/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author noelia
 */
public class TestP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TAREA 1. OOOK!!!!!!!
        /* 
            System.out.println("TAREA 1");
            int j1, j2, j3, j4; //para contar cuantas veces sale cada uno
            j1=j2=j3=j4=0;
            int temp=0;
            
            for (int i=0; i <100; i++) {
                temp = Dado.getInstance().quienEmpieza(4);
                if (temp == 0) {
                    j1++;
                } else if(temp == 1) {
                    j2++;
                } else if(temp == 2) {
                    j3++;
                } else {
                    j4++;
                }
            }
            
            System.out.println(j1);
            System.out.println(j2);        
            System.out.println(j3);
            System.out.println(j4);
            System.out.println(); //para diferenciar tareas
        */        
        // TAREA 2. OOOOK!!!!!

        /*
            System.out.println("TAREA 2");
            Dado.getInstance().setDebug(false); 
            for (int i=0; i < 10; i++) {
                System.out.println(Dado.getInstance().tirar());
            }
            System.out.println();
            Dado.getInstance().setDebug(true); 
            for (int i=0; i < 10; i++) {
                System.out.println(Dado.getInstance().tirar());
            }
            System.out.println();
        */
        
        // TAREA 3. OOOOK!!!!!!!!!!!!
        
        /* 
            System.out.println("TAREA 3");
            System.out.println(Dado.getInstance().getUltimoResultado()); //debería dar 0 por el constructor    
            System.out.println(Dado.getInstance().salgoDeLaCarcel()); //depende de la tirada que se lleva a cabo cuando se llama al método
            System.out.println("TIRAMOS DADO, Y VEMOS ULTIMO RESULTADO"); //salgoDeLaCarcel comprobado YA!! (substituido por ultimo... pq así se veía el resultado
            System.out.println(Dado.getInstance().tirar());
            System.out.println(Dado.getInstance().getUltimoResultado());
            System.out.println(); //para diferenciar tareas
        */

        // TAREA 4. DEBERIA DAR ESTO NO?
        
        /*
            System.out.println("TAREA 4");
            System.out.println(TipoCasilla.DESCANSO);
            System.out.println(TipoSorpresa.IRCARCEL);
            System.out.println(); // para diferenciar tareas
        */
            
        // TAREA 5. OOOOK!!!
 
        /*
            System.out.println("TAREA 5");
            
            MazoSorpresas obj = new MazoSorpresas();
            Sorpresa sorpresa2 = new Sorpresa("sorpresa2"); //esto tiene sentido cuando habia un constructor inventado en la clase sorpresa
            Sorpresa sorpresa1 = new Sorpresa("sorpresa1"); //esto tiene sentido cuando habia un constructor inventado en la clase sorpresa
            
            obj.alMazo(sorpresa1);
            obj.alMazo(sorpresa2);
            
            System.out.println(obj.Siguiente()); //como no ha usado ninguna no mezcla, la que tenia la borra
            System.out.println(obj.Siguiente()); // coge la siguiente (la unica que queda)
            System.out.println(obj.Siguiente()); // recupera, mezcla las cartas y devuelve la primera
            System.out.println(obj.Siguiente()); // y así sucesivamente

            obj.inhabilitarCartaEspecial(sorpresa2);
            System.out.println(Diario.getInstance().leerEvento()); //deberia que se ha inhabilitado
    
            obj.habilitarCartaEspecial(sorpresa2);
            System.out.println(Diario.getInstance().leerEvento()); //deberia que se ha inhabilitado
 
            System.out.println();       
        */
        
        // TAREA 6. OOOOK !!!!!!!
        
        /*
            System.out.println("TAREA 6");
            System.out.println(Diario.getInstance().eventosPendientes()); //debería dar false
            Diario.getInstance().ocurreEvento("EVENTO X PROCESANDO");
            System.out.println(Diario.getInstance().eventosPendientes()); //debería dar true
            System.out.println(Diario.getInstance().leerEvento()); //debería de mostrar lo escrito con ocurreEvento
        */

        // TAREA 7

        /*
            Tablero temporal = new Tablero(6); //creamos un tablero que puede contener hasta 6 casillas (una siendo para la carcel, y la otra para la salida)

            //añadimos varias casillas
            
            Casilla uno = new Casilla("uno");
            Casilla dos = new Casilla("dos");
            Casilla tres = new Casilla("tres");
            Casilla cuatro = new Casilla("cuatro");
            
            temporal.añadeCasilla(uno);
            temporal.añadeCasilla(dos);
            temporal.añadeCasilla(tres);
            temporal.añadeCasilla(cuatro);
            
            temporal.añadeJuez();
            
            int num = Dado.getInstance().tirar(); 
            
            System.out.println(num);
           // System.out.println(temporal.getCarcel());
            
            System.out.println(temporal.nuevaPosicion(1, num));
            System.out.println(temporal.calcularTirada(1, num));
            
            System.out.println();
        */
        
    }
    
}
