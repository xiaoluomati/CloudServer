package cn.edu.nju.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "light")
public class LightDataModel {

    @Id
    private String _id;

    @Field("data")
    private String data;

    @Field("time")
    private Date time;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LightDataModel{" +
                "data='" + data + '\'' +
                ", time=" + time +
                '}';
    }
}
