/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego-d
 */
public class Server {

    private final static int port = 5000;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(ChatSocket.PORT);
            Socket socket = serverSocket.accept();
            ChatSocket chatSocket = new ChatSocket(socket);
            ChatFrame chatFrame = new ChatFrame("Chat Servidor");
            chatFrame.setSocket(chatSocket);
            chatFrame.initView();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
