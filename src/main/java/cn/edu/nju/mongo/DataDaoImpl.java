package cn.edu.nju.mongo;

import cn.edu.nju.model.DataModel;
import cn.edu.nju.model.LightDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DataDaoImpl implements DataDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<DataModel> findTempData(Date startTime, Date endTime) {
        Query query = new Query(Criteria.where("time").gte(startTime).lt(endTime));
        List<DataModel> entList = mongoTemplate.find(query, DataModel.class);
        return entList;
    }

    @Override
    public List<LightDataModel> findLightData(Date start, Date end) {
        Query query = new Query(Criteria.where("time").gte(start).lt(end));
        List<LightDataModel> entList = mongoTemplate.find(query, LightDataModel.class);
        return entList;
    }
}
