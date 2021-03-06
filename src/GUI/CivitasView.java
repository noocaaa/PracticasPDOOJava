/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.CivitasJuego;
import civitas.Jugador;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 * @author noelia
 */
public class CivitasView extends javax.swing.JFrame {
    
    //Instancia de la clase CivitasJuego (punto 3)
    private CivitasJuego juego;
    private JugadorPanel jugadorPanel = new JugadorPanel();
    private GestionarDialog gestionarD = new GestionarDialog(this, true);

    public void setCivitasJuego(CivitasJuego juego){
        this.juego = juego;
        this.setVisible(true); //La vista se pone visible (punto 4)
        Ranking.setVisible(false);
        jTextArea1.setVisible(false);
    }    
    
   
    public CivitasView() {
        initComponents();
        
        jugadorPanel = new JugadorPanel();
        contenedorVistaJugador.add (jugadorPanel);        
        repaint();
        revalidate();
    }
    
    public void actualizarVista(){       
        jugadorPanel.setJugador(juego.getJugadorActual());        
        jTextField1.setText(juego.getCasillaActual().toString());
        jugadorPanel.setVisible(true);
                
        if(juego.finalDelJuego()){
            Ranking.setVisible(true);
            
            ArrayList<Jugador> rank = juego.ranking(); 
            String r = "";
            for (int i = 0; i < rank.size(); i++){
                r = r + rank.get(i).toString() + "\n"; 
            }
            
            jTextArea1.setText(r);
            jTextArea1.setVisible(true);
        }
        
        repaint();
        revalidate();
    }   
    
    void mostrarEventos(){
        DiarioDialog diarioD = new DiarioDialog(this); //crea la venta del diario
        diarioD.repaint();
        diarioD.revalidate();
        
    }
    
    public void mostrarSiguienteOperacion(OperacionesJuego operacion){
        nop.setText("" + operacion);
        nop.setVisible(true);
        actualizarVista();
    }

    public Respuestas comprar(){
        int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres comprar la calle actual?", "Compra", JOptionPane.YES_NO_OPTION);
        
        return (Respuestas.values()[opcion]);
    }
    
    public int getGestion(){ return gestionarD.getGestion(); }
    
    public int getPropiedad(){ return gestionarD.getPropiedad(); }
    
    public void gestionar(){
        gestionarD.gestionar(juego.getJugadorActual());
        
        gestionarD.pack();
        gestionarD.repaint();
        gestionarD.revalidate();
        
        gestionarD.setVisible(true);
    }
    
    public SalidasCarcel salirCarcel(){
        String[] opciones= {"Pagando", "Tirando"};
        
        int respuesta= JOptionPane.showOptionDialog(null, "¿Cómo quieres salir de la cárcel?",
        "Salir de la cárcel", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[0] );
        
        return (SalidasCarcel.values()[respuesta]);
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        contenedorVistaJugador = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        NextOperation = new javax.swing.JLabel();
        nop = new javax.swing.JTextField();
        Ranking = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Titulo.setText("Civitas Juego");

        jTextField1.setEditable(false);
        jTextField1.setText("jTextField1");
        jTextField1.setBorder(new javax.swing.border.MatteBorder(null));

        NextOperation.setText("Siguiente operación:");

        nop.setText("jTextField2");
        nop.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nop.setEnabled(false);
        nop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nopActionPerformed(evt);
            }
        });

        Ranking.setText("Ranking:");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NextOperation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Ranking)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 845, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextOperation)
                    .addComponent(nop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Ranking)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 366, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nopActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CivitasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NextOperation;
    private javax.swing.JLabel Ranking;
    private javax.swing.JLabel Titulo;
    private javax.swing.JPanel contenedorVistaJugador;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField nop;
    // End of variables declaration//GEN-END:variables
}
