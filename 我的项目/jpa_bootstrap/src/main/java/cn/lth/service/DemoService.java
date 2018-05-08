package cn.lth.service;

import cn.lth.dao.DemoDao;
import cn.lth.dto.DemoDto;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public DemoDto save(DemoDto demoDto){
        if(demoDto == null){
            throw new RuntimeException("demoDto.null.error");
        }
        boolean con = !StringUtils.hasText(demoDto.getContent().trim());
        boolean name = !StringUtils.hasText(demoDto.getName().trim());
        boolean some = !StringUtils.hasText(demoDto.getSomething().trim());
        if(con || name || some){
            throw new RuntimeException("demoDto.property.null.error");
        }
        DemoDto demoDto1 = demoDao.save(demoDto);
        return  demoDto1;
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

    /**
     * 查询
     *
     * @param id
     * @return
     */
    public DemoDto findById(Integer id){
        Optional<DemoDto> byId = demoDao.findById(id);
        return  byId.get();
    }

    /**
     * 修改
     *
     * @param DemoDto
     * @return
     */
    @Transactional
    public DemoDto updateDemo(DemoDto DemoDto){
        return demoDao.saveAndFlush(demoDao);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delDemo(Integer id){
        demoDao.deleteById(id);
    }
}
