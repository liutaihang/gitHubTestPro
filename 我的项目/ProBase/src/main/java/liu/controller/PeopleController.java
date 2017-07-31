package liu.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import liu.manager.PeopleManager;

@RestController
@RequestMapping(value = "people")
public class PeopleController{
	 private static Logger logger =LoggerFactory.logger(PeopleController.class);
	@Resource
	private PeopleManager peopleManager;
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public Map<String, Object> findAll(){
		logger.info("lai----------------->");
		return peopleManager.getAll();
	}

	
}
