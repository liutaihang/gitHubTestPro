package liu.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import liu.po.msq.Animal;
import liu.service.AnimalService;

@Component
public class AnimalManager {

	@Resource
	private AnimalService service;
	
	public Map<String, Object> add(Animal animal){
		Map<String, Object> data = new HashMap<>();
		service.add(animal);
		data.put("data", animal);
		return data;
	}
	
	public Map<String, Object>findAll(){
		Map<String, Object> data = new HashMap<>();
		List<Animal> target = service.findAll();
		data.put("data", target);
		return data;
	}
}
