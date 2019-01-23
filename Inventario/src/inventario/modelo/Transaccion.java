/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.modelo;

import inventario.controlador.Control;

/**
 *
 * @author Nao Rojas
 */
public class Transaccion implements Runnable {

    private int x;
    private Thread hiloControl;
    boolean continuar;
    private Control gestor;
    private double MIN_ANGULO = -100;
    private double MAX_ANGULO = 100;

    public Transaccion(int incremento) {
        this.x = incremento;
        hiloControl = new Thread(this);
    }

    public Transaccion(Control c) {
        hiloControl = new Thread(this);
        gestor = c;
        x=0;
    }

    public void init() {
        continuar = true;
        hiloControl.start();
    }

//    public void contar() {
//        try {
//            while (continuar) {
//                //x++;
//                String.format("%d TX/por minuto",x);
//                hiloControl.sleep(500);
//
//            }
//        } catch (InterruptedException ex) {
//        }
//
//    }

    public void finalizar() {
        continuar = false;
        try {
            hiloControl.join();

        } catch (InterruptedException ex) {
        }
    }

    public void setTX() {
       gestor.setTX(x);
       
    }

    public boolean getContinuar() {
        return continuar;
    }

    @Override
    public void run() {

        try {
            while (continuar) {
                //x++;
                String.format("%d TX/por minuto",x);
                hiloControl.sleep(10);

            }
        } catch (InterruptedException ex) {
        }


    }
}
