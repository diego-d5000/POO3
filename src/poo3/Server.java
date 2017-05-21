/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        
        System.out.println("Iniciando Servidor \n\n\n");

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Esperando... \n");
            Socket client = serverSocket.accept();
            
            BufferedReader in
                    = new BufferedReader(
                            new InputStreamReader(client.getInputStream()));

            PrintWriter out
                    = new PrintWriter(client.getOutputStream(), true);

            GreetingsProtocol greetingPClient = new GreetingsProtocol.Server();

            String inputLine;
            String outputLine;
            
            
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                outputLine = greetingPClient.getOutputMessage();
                out.println(outputLine);
                if (outputLine == null) {
                    break;
                }
            }

            in.close();
            out.close();
            client.close();
            serverSocket.close();
        } catch (Exception e) {
        }
    }

}
