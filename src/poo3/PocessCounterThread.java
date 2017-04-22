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
public class PocessCounterThread extends Thread {

    private final long HALF_SECOND = 500;
    private int counter;
    private int multiple;
    private String threadName;
    private TimerEndListener callback;

    public PocessCounterThread(int initialCounter, String threadName, TimerEndListener callback) {
        this.counter = initialCounter;
        this.multiple = initialCounter;
        this.callback = callback;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(String.valueOf(counter) + " en " + threadName);
            if (counter == multiple * 10) {
                callback.onEnd();
                return;
            }
            counter += multiple;
            try {
                sleep(HALF_SECOND);
            } catch (InterruptedException ex) {
                Logger.getLogger(PocessCounterThread.class.getName()).log(Level.SEVERE, null, ex);
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
