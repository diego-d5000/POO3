/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego-d
 */
public class Client {
    public static void main(String[] args) {
        
        try {
            Socket socket = new Socket(ChatSocket.LOCALHOST, ChatSocket.PORT);
            ChatSocket chatSocket = new ChatSocket(socket);
            ChatFrame chatFrame = new ChatFrame("Chat Cliente");
            chatFrame.setSocket(chatSocket);
            chatFrame.initView();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
