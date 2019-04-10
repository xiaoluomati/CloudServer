package cn.edu.nju.Service;

import cn.edu.nju.model.DataModel;
import cn.edu.nju.model.LightDataModel;
import cn.edu.nju.mongo.DataRepository;
import cn.edu.nju.mongo.LightDataRepository;
import cn.edu.nju.mongo.DataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private LightDataRepository lightDataRepository;

    @Autowired
    private DataDao tempDao;

    @Override
    public List<DataModel> getTemperatureData() {
        return dataRepository.findAll();
    }

    @Override
    public List<LightDataModel> getLightData() {
        return lightDataRepository.findAll();
    }

    @Override
    public List<DataModel> getTempData(Date start, Date end) {
        List<DataModel> tempData = tempDao.findTempData(start, end);
        if(tempData == null){
            return new ArrayList<>();
        }else{
            return tempData;
        }
    }

    @Override
    public List<LightDataModel> getLightData(Date start, Date end) {
        List<LightDataModel> lightData = tempDao.findLightData(start, end);
        if(lightData == null){
            return new ArrayList<>();
        }else{
            return lightData;
        }
    }
}
