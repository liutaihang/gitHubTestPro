package cn.lth.mongoDao;

import cn.lth.dto.MsgInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MsgInfoDao extends MongoRepository<MsgInfo, String> {
}
