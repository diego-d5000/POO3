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
public class SecondsTimerThread extends Thread {
    private long counter;
    private TimerEndListener callback;

    public SecondsTimerThread(long millisStart, TimerEndListener callback) {
        this.counter = millisStart;
        this.callback = callback;
    }
    
    

    @Override
    public void run() {
        while (true) {
            System.err.println(counter);
            counter--;
            if(counter < 0){
                callback.onEnd();
                return;
            }
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SecondsTimerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public long getCounter() {
        return counter;
    }

    public static interface TimerEndListener {
        void onEnd();
    }

}
