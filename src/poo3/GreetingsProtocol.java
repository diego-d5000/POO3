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
public class GreetingsProtocol {

    private static final int GREETING = 1;
    private static final int MESSAGE = 2;
    private static final int GOODBYE = 3;
    private static final int FINISHED = 4;

    private int state;
    String[] messages;

    public GreetingsProtocol(int state) {
        this.state = state;
    }

    public String getOutputMessage() {

        String theOutput = null;

        if (state == GREETING) {
            theOutput = messages[0];
            state = MESSAGE;
        } else if (state == MESSAGE) {
            theOutput = messages[1];
            state = GOODBYE;
        } else if (state == GOODBYE) {
            theOutput = messages[2];
            state = FINISHED;
        }

        return theOutput;
    }

    public static class Client extends GreetingsProtocol {

        public Client() {
            super(1);
            this.messages = new String[]{"Hola Servidor",
                "Como estas",
                "Hasta la proxima"};
        }

    }

    public static class Server extends GreetingsProtocol {

        public Server() {
            super(1);
            this.messages = new String[]{"Peticion recibida y aceptada",
                "Gracias por usar nuestros servicios",
                "Que tengas un buen dia"};
        }

    }

}
