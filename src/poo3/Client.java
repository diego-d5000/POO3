/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego-d
 */
public class Client {

    private final static String localhost = "localhost";
    private final static int port = 5000;

    public static void main(String[] args) {
        System.out.println("Iniciando Cliente \n\n");

        try {

            Socket socket = new Socket(localhost, port);

            BufferedReader in
                    = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            PrintWriter out
                    = new PrintWriter(socket.getOutputStream(), true);

            GreetingsProtocol greetingPClient = new GreetingsProtocol.Client();

            String inputLine = "";
            String outputLine;

            do {
                System.out.println(inputLine);
                outputLine = greetingPClient.getOutputMessage();
                if (outputLine == null) {
                    break;
                }
                out.println(outputLine);
            } while ((inputLine = in.readLine()) != null);
            
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
        }

    }

}
