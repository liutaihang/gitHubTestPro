package liu.dao.mongo.BasePro;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Query;

/**
 * mongodb-base_dao
 *
 */
public class mongoDataBase<T> {
	@Autowired
	private  MongoTemplate Template;
	
	private Class<T> classType;
	
    @SuppressWarnings("unchecked")
	public mongoDataBase() {
    	Type superClass = this.getClass().getGenericSuperclass();
		// 获取参数泛型的类
		if (superClass instanceof ParameterizedType) {
			this.classType = (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
		}
	}
    
    public long count(Query query, String collectionName){
    	return Template.count(query, collectionName);
    }
    
    public T save(T t){
    	isAnnotation(t.getClass());
    	Template.save(t, getCollectionName(t.getClass()));
    	return t;
    }
    
    public List<T> list(Query query){
    	return Template.find(query, classType);
    }
    
    public List<T> findAll(){
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
}
