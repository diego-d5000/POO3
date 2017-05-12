/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego-d
 */
public class POO3 {
    
    static final int MAX = 100;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] elements = new int[100];
        for(int i = 0; i < elements.length; i++){
            Random random = new Random();
            elements[i] = random.nextInt(MAX);
        }
        
        new MainWindow(elements).setVisible(true);

    }

}
