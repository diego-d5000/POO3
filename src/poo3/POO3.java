/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego-d
 */
public class POO3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new PocessCounterThread(2, "Hilo 1", new PocessCounterThread.TimerEndListener() {
            @Override
            public void onEnd() {
                System.out.println("Hilo 1 Terminado");
            }
        }).start();
        
        new PocessCounterThread(3, "Hilo 2", new PocessCounterThread.TimerEndListener() {
            @Override
            public void onEnd() {
                System.out.println("Hilo 2 Terminado");
            }
        }).start();

    }

}
