package cn.edu.nju.socket;

import cn.edu.nju.model.LightDataModel;
import cn.edu.nju.mongo.LightDataRepository;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MyWebsocketServer {

    @Autowired
    LightDataRepository lightRepository;

    @PostConstruct
    public void startServer(){
        WebSocketServer server = new WebSocketServer(new InetSocketAddress("localhost", 1551)) {
            @Override
            public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {

            }

            @Override
            public void onClose(WebSocket webSocket, int i, String s, boolean b) {

            }

            @Override
            public void onMessage(WebSocket webSocket, String message) {
                System.out.println("received message : " + message);
                List<LightDataModel> lightDataModels = new Vector<>();
                String[] split = message.split(";");
                String regex = "data:(.*),time:(.*)";
                Pattern pattern = Pattern.compile(regex);

                for (String s1 : split) {
                    Matcher matcher = pattern.matcher(s1);
                    if(!matcher.find()){
                        continue;
                    }
                    LightDataModel lightDataModel = new LightDataModel();
                    lightDataModel.setData(matcher.group(1));
                    Date date = new Date();
                    date.setTime(Long.parseLong(matcher.group(2)));
                    lightDataModel.setTime(date);
                    lightDataModels.add(lightDataModel);
                }
                lightRepository.saveAll(lightDataModels);
            }

            @Override
            public void onError(WebSocket webSocket, Exception e) {

            }

            @Override
            public void onStart() {

            }
        };
        server.start();
    }
}
