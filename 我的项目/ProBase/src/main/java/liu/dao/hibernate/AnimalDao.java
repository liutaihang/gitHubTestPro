package liu.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import liu.dao.BaseDao.MysqlDataBase;
import liu.po.msq.Animal;

@Repository
@Transactional
public class AnimalDao extends MysqlDataBase<Animal, Integer>{

	public Animal add(Animal animal){
		return super.save(animal);
	}
	
	public List<Animal> findAll(){
		return super.findAll();
	}
	
	
}
