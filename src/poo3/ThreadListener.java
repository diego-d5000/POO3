/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

/**
 *
 * @author diego-d
 */
public interface ThreadListener {

    void onStart();
    void onSwap(int indexA, int newValueA, int indexB, int newValueB);
    void onFinish();
}
