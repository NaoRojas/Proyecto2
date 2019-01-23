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
        x = 0;
    }

    public void init() {
        continuar = true;
        hiloControl.start();
    }

    public void finalizar() {
        continuar = false;
        try {
            hiloControl.join();

        } catch (InterruptedException ex) {
        }
    }

    public boolean getContinuar() {
        return continuar;
    }

    @Override
    public void run() {

        try {
            while (continuar) {

                gestor.setTX();
                System.out.printf("TX POR MINUTO %d \n", x);
                Thread.sleep(500);

            }
        } catch (InterruptedException ex) {
        }

    }
}
