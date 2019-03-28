package cn.edu.nju.mqtt;

import cn.edu.nju.model.DataModel;
import cn.edu.nju.mongo.DataRepository;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MqttClientCallBack implements MqttCallback {

    private DataRepository dataRepository;

    public MqttClientCallBack(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

        List<DataModel> dataModels = new Vector<>();
        String message = mqttMessage.toString();
        
        String[] split = message.split(";");
        String regex = "data:(.*),time:(.*)";
        Pattern pattern = Pattern.compile(regex);

        for (String s1 : split) {
            Matcher matcher = pattern.matcher(s1);
            if(!matcher.find()){
                continue;
            }
            DataModel dataModel = new DataModel();
            dataModel.setData(matcher.group(1));
            Date date = new Date();
            date.setTime(Long.parseLong(matcher.group(2)));
            dataModel.setTime(date);
            dataModels.add(dataModel);
        }
        for (DataModel dataModel : dataModels) {
            System.out.println("dataModel = " + dataModel);
        }
        dataRepository.saveAll(dataModels);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
