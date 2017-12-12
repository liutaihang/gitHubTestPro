package cn.lth.threadDemo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DemoRun implements Runnable{

	private Object obj;
	
	public DemoRun() {}
	public DemoRun(Object obj){
		this.obj = obj;
	}
	public DemoRun(DemoInterface demo){}
	
	@Override
	public void run() {
		if(obj != null){
			Class clazz = obj.getClass();
			StringBuffer buffer = new StringBuffer();
			buffer.append(clazz.getName()).append(".");
			Field [] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				System.out.println(buffer.append(field.getName()).toString());
			}
			Method[]methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				System.out.print(method.getName() + "====");
				if("run".equals(method.getName())){
					try {
						method.invoke(obj);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
