package cn.edu.nju.mongo;

import cn.edu.nju.model.DataModel;
import cn.edu.nju.model.LightDataModel;

import java.util.Date;
import java.util.List;

public interface DataDao {

    List<DataModel> findTempData(Date start, Date end);

    List<LightDataModel> findLightData(Date start, Date end);

}
