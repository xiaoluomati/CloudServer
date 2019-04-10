package cn.edu.nju.Service;

import cn.edu.nju.model.DataModel;
import cn.edu.nju.model.LightDataModel;

import java.util.Date;
import java.util.List;

public interface DataService {

    List<DataModel> getTemperatureData();

    List<LightDataModel> getLightData();

    List<DataModel> getTempData(Date start, Date end);

    List<LightDataModel> getLightData(Date start, Date end);
}

