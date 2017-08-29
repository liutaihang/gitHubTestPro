package liu.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class CheckPath {
	
	/**
	 * 转换枚举类为json格式的string
	 */
	public static <T extends Enum<T>> String convertSpitChat(Class<T> class1) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder sb = new StringBuilder("");
		String className = "";
		Map<String, List<Map<String, Object>>> enumMap = new HashMap<>();
		List<Map<String, Object>> datas = new ArrayList<>();
		for (T val : EnumSet.allOf(class1)) {
				//sb.append(tmp.name()).append(SPIT_CHAR);
				Class<? extends Enum> clazz = val.getClass();
				className = clazz.getSimpleName();
				Field[] declaredFields = clazz.getDeclaredFields();
				Map<String, Object> data = new HashMap<>(declaredFields.length);
				data.put("name", val.name());
				data.put("ordinal", val.ordinal());
				for(Field f : declaredFields){
//						f.setAccessible(true);
//						Object str = f.get(val);
//						System.err.println(str);
//					Modifier.isFinal(f.getModifiers());
					boolean isStatic = Modifier.isStatic(f.getModifiers());
					if (!isStatic) {
						f.setAccessible(true);
//						Object str1 = f.get(val);
//						System.out.println(str1);
						
						Object fValue = null;
						try {
							fValue = f.get(val);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
						if (!f.isEnumConstant()) {
							data.put(f.getName(), fValue);
						}
					}
			}
			datas.add(data);
		}
		enumMap.put(className, datas);
		return JSONObject.toJSONString(enumMap);
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		Set<String> allClassPath = PackageUtil.findPackageClass("liu.enums");
		StringBuilder str = new StringBuilder("[");
		
		for(String classPath : allClassPath){
			try {
				Class forName = Class.forName(classPath);
				if(forName.isEnum()){
					str.append(CheckPath.convertSpitChat(forName)).append(",");
					System.out.println(CheckPath.convertSpitChat(forName));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		str.append("]");
		System.out.println(str.toString());
	}
}
