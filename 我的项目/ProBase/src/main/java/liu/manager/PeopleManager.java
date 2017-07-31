package liu.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import liu.po.People;
import liu.service.PeopleService;

@Component
public class PeopleManager {

	@Resource
	private PeopleService peopleService;
	
	public Map<String, Object> getAll(){
		List<People> lists = peopleService.getAll();
		Map<String, Object> maps = new HashMap<>();
		maps.put("data", lists);
		maps.put("msg", "查询成功");
		return maps;
	}
}
