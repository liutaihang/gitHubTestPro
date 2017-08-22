package liu.service;

import java.util.List;


import liu.po.msq.Animal;

public interface AnimalService {

	public Animal add(Animal animal);
	
	public List<Animal> findAll();
}
