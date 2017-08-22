package liu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import liu.dao.hibernate.AnimalDao;
import liu.po.msq.Animal;
import liu.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService{

	@Resource
	private AnimalDao animalDao;
	
	@Override
	public Animal add(Animal animal) {
		return animalDao.add(animal);
	}

	@Override
	public List<Animal> findAll() {
		return animalDao.findAll();
	}

}
