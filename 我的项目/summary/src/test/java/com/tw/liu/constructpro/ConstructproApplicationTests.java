package com.tw.liu.constructpro;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.tw.liu.constructpro.JsonUtils.ConvertDto;
import com.tw.liu.constructpro.JsonUtils.CreationUtils;
import com.tw.liu.constructpro.dao.AreaDao;
import com.tw.liu.constructpro.entity.SysArea;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstructproApplicationTests {

    private Logger log = LoggerFactory.getLogger(ConstructproApplicationTests.class);

    @Autowired
    private AreaDao areaDao;

    @Test
    public void contextLoads() {
        log.error("demosssss");
        log.debug("sssssdemo");
    }


    @Test
    public void converJson() {
        String jsonData = CreationUtils.getJsonData("C:/Users/￥/Desktop/json.json");
//        JSONArray array = JSON.parseArray(jsonData);

        Gson gson = new Gson();
        ConvertDto convertDto = gson.fromJson(jsonData, ConvertDto.class);
        List<SysArea> sysAreas = get(convertDto, 0);
        System.out.println(sysAreas.toString());
        areaDao.saveAll(sysAreas);
//        List<Object> objects =Arrays.asList(array.toArray());
//        objects.stream().map(tem -> {
//            return (JSONObject) tem;
//        }).collect(Collectors.toList());

    }

    private static List<SysArea> areas = Lists.newArrayList();
    private static int no = 1;

    public static List<SysArea> get(ConvertDto convertDto, int sum){
        no ++;
        if(null == convertDto.getSum()){
            convertDto.setSum(1);
        }
        SysArea area = new SysArea();
        String uid = UUID.randomUUID().toString();
        if(convertDto.getAreaName().equals("中国")){
            uid = "1";
        }
        area.setCode(convertDto.getAreaId());
        area.setId(uid);
        area.setName(convertDto.getAreaName());
        if(convertDto.getSum() == 1){
            area.setParentId("0");
            area.setParentIds("0,");
            area.setType(convertDto.getSum() + "");
        }
        if(convertDto.getSum() == 2){
            area.setParentId("1");
            area.setParentIds("0,1,");
            area.setType("2");
        }
        if(convertDto.getSum() == 3){
            area.setParentId(convertDto.getPid());
            area.setParentIds(convertDto.getPids());
            area.setType("3");
        }
        if(convertDto.getSum() == 4){
            area.setParentId(convertDto.getPid());
            area.setParentIds(convertDto.getPids());
            area.setType("4");
        }
        area.setSort(no);
        areas.add(area);
        int i = 1;
        if(null != convertDto.getAearList()){
            List<ConvertDto> aearList = convertDto.getAearList();
            for (ConvertDto obj: aearList) {
                i++;
                //判断第几层
                if(null != convertDto.getSum()){
                    int num = convertDto.getSum() + 1;
                    obj.setSum(num);
                    obj.setPid(uid);
                    obj.setPids(area.getParentIds() + uid + ",");
                }
                get(obj, sum);
            }
            no ++;
        }
        System.out.println(no);
        return areas;
    }

    public static void main(String[] args) {
//        while(true){
//            Scanner scanner =  new Scanner(System.in );
//            String question = scanner.nextLine();
//            String answer = question.replace("你", "我").replace("吗"," ").replace("？", "!").replace("?", "!");
//            System.out.println(answer);
//        }


    }
}
