package liu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import liu.constant.Constant;
import liu.po.Book;

public class CheckPath {
	
	/**
	 * 转换枚举类为json格式的string
	 */
	public static <T extends Enum<T>> String convertSpitChat(Class<T> class1) throws IllegalArgumentException, IllegalAccessException {
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
					boolean isStatic = Modifier.isStatic(f.getModifiers());
					if (!isStatic) {
						f.setAccessible(true);
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
				e.printStackTrace();
			}
		}
		str.append("]");
		System.out.println(str.toString());
		Class<Constant> clazz = Constant.class;
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			System.out.println(field.getName() + " : " +field.get(clazz));
		}
		
		try {
			CheckPath.copyFill();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件读取与写入
	 */
	public static void copyFill() throws UnsupportedEncodingException{
//		Files.copy(source.toPath(), dest.toPath());
		Book book = new Book();
		book.setBookId(1);
		book.setName(new String("金瓶梅".getBytes("utf-8")));
		book.setNumber(12);
		
		try {
			ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream("d:/temp.txt"));
			write.writeObject(book);
			write.close();
			
			ObjectInputStream read = new ObjectInputStream(new FileInputStream("d:/temp.txt"));
			try {
				Object obj = read.readObject();
				System.out.println(obj);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			FileWriter writer = new FileWriter("d:/temp.txt");
//			writer.write(new String("测试用的".getBytes("utf8"),"utf8"));
//			writer.close();
			
			InputStream ins = new FileInputStream("C:/Users/Administrator/Desktop/枚举json.txt");
			InputStreamReader reader = new InputStreamReader(ins);
			BufferedReader re = new BufferedReader(reader);
			if(re.ready()){
				String str = re.readLine();
				while(str != null){
					System.out.println(new String(str.getBytes("utf8"), "utf8"));
					str = re.readLine();
				}
			}
			ins.close();
			reader.close();
			re.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
