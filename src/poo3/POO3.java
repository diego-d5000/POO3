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
        RequestThread requestThread = new RequestThread("http://time.jsontest.com/");
        requestThread.start();

        SecondsTimerThread secondsTimerThread = new SecondsTimerThread(10, new SecondsTimerThread.TimerEndListener() {
            @Override
            public void onEnd() {
                RequestThread requestThread = new RequestThread("http://time.jsontest.com/");
                requestThread.start();
            }
        });

        secondsTimerThread.start();

        try {
            Thread.currentThread().sleep(20000);
        } catch (InterruptedException ex) {
            Logger.getLogger(POO3.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestThread requestThreadTwo = new RequestThread("http://time.jsontest.com/");
        requestThreadTwo.start();
    }

}
