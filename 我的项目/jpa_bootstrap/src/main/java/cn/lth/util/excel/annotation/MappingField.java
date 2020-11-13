package cn.lth.util.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 映射excel列字段
 * @ClassName: MappingField
 * @Description: TODO
 * @author dh
 * @date 2020年6月8日
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingField {

	/**
	 * 对应的列号
	 * @Title: clolumnNumber
	 * @Description: TODO
	 * @return  
	 * @return int
	 */
	int clolumnNumber();
	
	/**
	 * 如果此字段需要翻译则需要添加此
	 * @Title: translate
	 * @Description: TODO
	 * @return  
	 * @return String
	 */
	String translate() default "";
	
	/**
	 * 如果是图片则此字段为true，默认为false
	 * @Title: isPicture
	 * @Description: TODO
	 * @return  
	 * @return boolean
	 */
	boolean isPicture() default false;
	
}
