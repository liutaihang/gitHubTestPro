package cn.lth.dao;

import cn.lth.dto.DemoDto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liutaihang
 * @version : 1.0
 * @Date : Create in 10:08 2018/4/11
 * @Description : ${TODO}
 */
public interface DemoDao extends JpaRepository<DemoDto, Integer>{

}
