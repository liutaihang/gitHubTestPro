package cn.lth;

import cn.lth.dto.DemoDto;
import cn.lth.dto.MsgInfo;
import cn.lth.mongoDao.MsgInfoDao;
import cn.lth.service.MsgInfoService;
import cn.lth.util.JTimeUtils;
import com.mongodb.MongoClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebJapApplication.class)
public class MongoTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoOperations jBootBase = new MongoTemplate(client, "JBootBase");
//        jBootBase.insert(new DemoDto("joe", "插入数据", "测试数据"));

//        UpdateResult demoDtos = jBootBase.updateFirst(new Query(where("name").is("joe")), Update.update("something", 123456), DemoDto.class);
//        List<DemoDto> demoDtos = jBootBase.find(new Query(where("name").is("joe")), DemoDto.class);
//        System.out.println(demoDtos);
        DemoDto demoDto = new DemoDto("joe1", "1234", "测试数据");
        demoDto.setId("5ec5f05832e49670c1a137c3");
        jBootBase.save(demoDto);
    }

    @Autowired
    MsgInfoService template;

    @Test
    public void templateTest(){
//        template.insert(new DemoDto("joe", "插入数据", "测试数据"));
//        List<DemoDto> one = template.find(new Query(where("name").is("joe")), DemoDto.class);
//        System.out.println(one);
//        Optional<MsgInfo> one = template.findById("5ec5f05832e49670c1a137c3");

//        Example<Object> of = Example.of(new DemoDto("joe", "123", "测试数据"), ExampleMatcher.matching().withIgnoreCase("name", "content"));
//        Optional one1 = template.findOne(of);
//        System.out.println(one1.get());

        MsgInfo msgInfo = new MsgInfo() {
            {
                setContent("测试");
                setCreateTime(JTimeUtils.getTimestamp(JTimeUtils.format_1));
                setReceiveId("12322");
                setReceiveName("接收人");
                setSendId("23111");
                setSendName("发送人");
            }
        };

        template.save(msgInfo);
//        template.insert()
        List<MsgInfo> all = template.findAll();
        System.out.println(all);
    }
}
