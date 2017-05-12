/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.util.Arrays;

/**
 *
 * @author diego-d
 */
public class BubbleSortMaxToMinThread extends Thread {

    private int[] elements;
    private ThreadListener listener;

    public BubbleSortMaxToMinThread(int[] elements) {
        this.elements = elements;
    }
    
    public void setOnSwapListener(ThreadListener listener){
        this.listener = listener;
    }

    @Override
    public void run() {
        int tmp;
        boolean swapped;

        listener.onStart();
        System.out.println(Arrays.toString(elements));

        do {
            swapped = false;
            for (int i = 0; i < elements.length - 1; i++) {
                if (elements[i] < elements[i + 1]) {
                    tmp = elements[i];
                    elements[i] = elements[i + 1];
                    elements[i + 1] = tmp;
                    swapped = true;
                    listener.onSwap(i, elements[i], i+1, elements[i+1]);
                }
            }
        } while (swapped);

        listener.onFinish();
        System.out.println(Arrays.toString(elements));
        
    }
}
