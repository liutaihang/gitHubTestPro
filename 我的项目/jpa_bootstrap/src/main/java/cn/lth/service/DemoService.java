package cn.lth.service;

import cn.lth.dao.DemoDao;
import cn.lth.dto.DemoDto;
import cn.lth.util.DemoException;
import cn.lth.util.ProLog;
import cn.lth.util.PropertiesUtils;
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
    private static PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();

    @Autowired
    private DemoDao demoDao;

    /**
     * save
     *
     * @param demoDto
     * @return
     */
    public DemoDto save(DemoDto demoDto) throws DemoException {
        if(demoDto == null){
            throw new DemoException(propertiesUtils.get("demoDto.null.error"));
        }
        boolean con = !StringUtils.hasText(demoDto.getContent().trim());
        boolean name = !StringUtils.hasText(demoDto.getName().trim());
        boolean some = !StringUtils.hasText(demoDto.getSomething().trim());
        if(con || name || some){
            throw new DemoException(propertiesUtils.get("demoDto.null.error"));
        }
        return  demoDao.save(demoDto);
    }

    /**
     * findAll
     *
     * @return
     */
    @ProLog(name = "sbss", value = "nnd", logType = ProLog.Type.SPECI)
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
    public DemoDto findById(String id){
        Optional<DemoDto> byId = demoDao.findById(id);
        return  byId.get();
    }

    /**
     * 修改
     *
     * @param demoDto
     * @return
     */
    @Transactional
    public DemoDto updateDemo(DemoDto demoDto){
        return demoDao.saveAndFlush(demoDto);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delDemo(String id){
        demoDao.deleteById(id);
    }
}
