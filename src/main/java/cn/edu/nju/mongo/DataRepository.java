package cn.edu.nju.mongo;

import cn.edu.nju.model.DataModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends MongoRepository<DataModel, String> {

}
