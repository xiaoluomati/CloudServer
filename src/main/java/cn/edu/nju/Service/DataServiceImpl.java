package cn.edu.nju.Service;

import cn.edu.nju.model.DataModel;
import cn.edu.nju.model.LightDataModel;
import cn.edu.nju.mongo.DataRepository;
import cn.edu.nju.mongo.LightDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private LightDataRepository lightDataRepository;

    @Override
    public List<DataModel> getTemperatureData() {
        return dataRepository.findAll();
    }

    @Override
    public List<LightDataModel> getLightData() {
        return lightDataRepository.findAll();
    }
}
