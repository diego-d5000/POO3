/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author diego-d
 */
public class RequestThread extends Thread {

    String url;

    public RequestThread(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            URL urlReq = new URL(url);

            HttpURLConnection connection;
            connection = (HttpURLConnection) urlReq.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader inBr = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = inBr.readLine()) != null) {
                response.append(inputLine);
            }
            inBr.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            System.err.println("e");
        }

    }

}
