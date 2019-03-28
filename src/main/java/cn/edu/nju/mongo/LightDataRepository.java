package cn.edu.nju.mongo;

import cn.edu.nju.model.LightDataModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LightDataRepository extends MongoRepository<LightDataModel, String> {
}
