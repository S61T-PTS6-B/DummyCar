/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.net.URI;
import java.net.URISyntaxException;
import model.CarLocation;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Max
 */
public class LocationSender {

    WebSocketClient mWs;

    public LocationSender() {

    }
    
    public void CloseConnection() {
        mWs.close();
    }

    public void StartSending() throws InterruptedException, URISyntaxException {

        mWs = new WebSocketClient(new URI("ws://145.93.85.180:8080/Verplaatsingsysteem/test/notifications"), new Draft_17()) {
            @Override
            public void onMessage(String message) {
                System.out.println(message);
            }

            @Override
            public void onOpen(ServerHandshake handshake) {
                System.out.println("opened connection");
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("closed connection");
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }

        };

        //open websocket
        mWs.connect();

        CarLocation car = new CarLocation();

        while (true) {
            Thread.sleep(5000);
            car.setCarId(1);
            car.setLatitude(car.getLatitude() + 0.0001);
            car.setLongitude(car.getLongitude() + 0.0002);

            JSONObject obj = new JSONObject();
            obj.put("lat", car.getLatitude());
            obj.put("long", car.getLongitude());
            obj.put("carId", car.getCarId());
            String message = obj.toString();

            mWs.send(message);
        }
    }

}
