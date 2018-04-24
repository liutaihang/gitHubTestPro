package cn.lth.service;

import cn.lth.dao.DemoDao;
import cn.lth.dto.DemoDto;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author liutaihang
 * @version : 1.0
 * @Date : Create in 16:15 2018/4/23
 * @Description : ${TODO}
 */
@Service
public class DemoService {

    @Autowired
    private DemoDao demoDao;

    /**
     * save
     *
     * @param demoDto
     * @return
     */
    public JsonObject save(DemoDto demoDto){
        JsonObject jsonObject = new JsonObject();
        if(demoDto == null){
            throw new RuntimeException("demoDto.null.error");
        }
        boolean con = !StringUtils.hasText(demoDto.getContent().trim());
        boolean name = !StringUtils.hasText(demoDto.getName().trim());
        boolean some = !StringUtils.hasText(demoDto.getSomething().trim());
        if(con && name && some){
            throw new RuntimeException("demoDto.property.null.error");
        }
        DemoDto demoDto1 = demoDao.save(demoDto);
        jsonObject.addProperty("result", demoDto1.toString());
        return  jsonObject;
    }

    /**
     * findAll
     *
     * @return
     */
    public List<DemoDto> findAll(){
        return  demoDao.findAll();
    }

    /**
     * 分页
     *
     * @param page
     * @return
     */
    public List<DemoDto> paginationList(Pageable page){
        Page<DemoDto> all = demoDao.findAll(page);
        return all.getContent();
    }

    /**
     * 条件查询
     *
     * @param pageable
     * @return
     */
    public List<DemoDto> findByList(Pageable pageable){

        return null;
    }

    public Long findAllNumber(){
        return demoDao.count();
    }
}
