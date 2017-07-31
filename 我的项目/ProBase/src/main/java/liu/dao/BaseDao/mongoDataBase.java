package liu.dao.BaseDao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;

/**
 * mongodb-base_dao
 *
 */
public class mongoDataBase<T> {
	@Autowired
	private  MongoTemplate Template;
	
	private Class<T> classType;
	
    @SuppressWarnings("unchecked")
    protected mongoDataBase() {
    	Type superClass = this.getClass().getGenericSuperclass();
		// 获取参数泛型的类
		if (superClass instanceof ParameterizedType) {
			this.classType = (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
		}
	}
    
    protected long count(Query query, String collectionName){
    	return Template.count(query, collectionName);
    }
    
    protected long remove(T t, String id){
    	Query query = new Query(Criteria.where("id").is(id));
    	return Template.remove(query, t.getClass()).getN();
    }
    
    protected T save(T t){
    	isAnnotation(t.getClass());
    	Template.save(t, getCollectionName(t.getClass()));
    	return t;
    }
    
    protected List<T> list(Query query){
    	return Template.find(query, classType);
    }
    
    protected List<T> findAll(){
    	return Template.findAll(classType);
    }
    
    private String getCollectionName(Class<?> clazz){
    	Document document = clazz.getAnnotation(Document.class);
    	if(document != null){
    		return document.collection();
    	}else{
    		System.err.println("实体没有注解{@Document}");
    		throw new RuntimeException();
    	}
    }
    
    /**
     * 判断是否有注解
     * @param clazz
     */
    private void isAnnotation(Class<?> clazz){
    	Document document = clazz.getAnnotation(Document.class);
    	if(document == null){
    		System.err.println("实体没有注解{@Document}");
    		throw new RuntimeException();
    	}
    	Field IdField = hasID(clazz);
    	if(IdField == null){
    		System.err.println("实体没有注解{@Id}");
    		throw new RuntimeException();
    	}
    }

    protected long update(T t){
		Field idField = hasID(classType);
		Object id = null;
		try {
			idField.setAccessible(true);
			id = idField.get(t);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			System.err.println("更新时对象id不能为空");
		}

		isAnnotation(t.getClass());
		BasicDBObject basicDBObject = new BasicDBObject("_id", id);

		Update update = buildBaseUpdate(t, classType, new Update());

		WriteResult result = Template.updateFirst(new BasicQuery(basicDBObject), update, classType);

		return result == null ? 0 : result.getN();
	}
    
    /**
     * 判断是否有@Id字段
     * @param clazz
     * @return
     */
	private Field hasID(Class<?> clazz) {
    	//先找到类中的声明字段
		Field []fields = clazz.getDeclaredFields();
    	
    	//再找到用了注解的字段
    	for (Field field : fields) {
    		Annotation []annotations = field.getAnnotations();
    		if(ArrayUtils.isNotEmpty(annotations)){
    			for (Annotation annotation : annotations) {
    				if(annotation.annotationType().equals(Id.class)){
    					return field;
    				}
    			}
    		}
		}
    	
    	//判断他所继承的父类对否有@Id的注解
    	if(null != clazz.getSuperclass()){
    		return hasID(clazz);
    	}
    	return null;
	}
	
	private Update buildBaseUpdate(T t, Class<?> clazz, Update update){

		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals("_id")) {
				continue;
			}
			if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
				continue;
			}

			field.setAccessible(true);
			// 过滤 STATIC 和 FINAL 修饰的属性
			if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			Object value = null;
			try {
				value = field.get(t);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				System.err.println("构建更新失败");
			}
			if (value != null) {
				update.set(field.getName(), value);
			}
		}

		// 需要考虑父类
		while (null != clazz.getSuperclass()) {
			clazz = clazz.getSuperclass();
			buildBaseUpdate(t, clazz, update);
		}

		return update;
	}
}
