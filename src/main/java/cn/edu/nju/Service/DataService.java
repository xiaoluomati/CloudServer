package cn.edu.nju.Service;

import cn.edu.nju.model.DataModel;
import cn.edu.nju.model.LightDataModel;

import java.util.List;

public interface DataService {

    List<DataModel> getTemperatureData();

    List<LightDataModel> getLightData();

}
