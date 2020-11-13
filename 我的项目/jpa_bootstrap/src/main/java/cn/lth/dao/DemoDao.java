package cn.lth.dao;

import cn.lth.base.BaseRepository;
import cn.lth.dto.DemoDto;

/**
 * @author liutaihang
 * @version : 1.0
 * @Date : Create in 10:08 2018/4/11
 * @Description : ${TODO}
 */
public interface DemoDao extends BaseRepository<DemoDto> {

    DemoDto saveAndFlush(DemoDto demoDto);
}
