/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego-d
 */
public class ChatSocket {

    public static final String LOCALHOST = "localhost";
    public static final int PORT = 5000;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ChatEventListener chatEventListener;

    public ChatSocket(Socket socket) {
        this.socket = socket;
        try {
            initStreams();
            initMessageReceiver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initStreams() throws IOException {
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        out = new PrintWriter(socket.getOutputStream(), true);
    }

    private void initMessageReceiver() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                String message;
                try {

                    while ((message = in.readLine()) != null) {
                        if (message != null && message.length() > 0) {
                            chatEventListener.messageReceived(message);
                        }

                        Thread.sleep(300);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(ChatSocket.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ChatSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(ChatSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };

        Thread thread = new Thread(task);
        thread.start();
    }

    public void sendMessage(String message) throws IOException {
        out.println(message);
        chatEventListener.messageSent(message);
    }

    public void disconnect() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    public void setChatEventListener(ChatEventListener chatEventListener) {
        this.chatEventListener = chatEventListener;
    }

    public static interface ChatEventListener {

        void messageSent(String message);

        void messageReceived(String message);
    }

}
